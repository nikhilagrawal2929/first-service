package com.espire.config;

import com.espire.model.Customer;
import com.espire.repository.CustomerRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private CustomerRepository customerRepository;

   /* @Bean
    public FlatFileItemReader<Customer> customerReader() {
        FlatFileItemReader<Customer> customerFlatFileItemReader = new FlatFileItemReader<>();
        customerFlatFileItemReader.setResource(new FileSystemResource("customers-1000.csv"));
        customerFlatFileItemReader.setName("CustomerFileReader");
        customerFlatFileItemReader.setLinesToSkip(1);
        customerFlatFileItemReader.setLineMapper(lineMapper());
        return customerFlatFileItemReader;

    }*/

    private LineMapper<Customer> lineMapper() {
        DefaultLineMapper<Customer> customerDefaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("index", "customerId", "firstName", "lastName", "company", "city", "country", "phone1", "phone2", "email", "subscriptionDate", "website");

        BeanWrapperFieldSetMapper<Customer> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(Customer.class);

        customerDefaultLineMapper.setLineTokenizer(lineTokenizer);
        customerDefaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
        return customerDefaultLineMapper;

    }
    @Bean
    public FlatFileItemReader<Customer> reader() {
        return new FlatFileItemReaderBuilder<Customer>()
                .name("CustomerFileReader")
                .resource(new ClassPathResource("customers-1000.csv"))
                .delimited()

                .names("index", "customerId", "firstName", "lastName", "company", "city", "country", "phone1", "phone2", "email", "subscriptionDate", "website")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Customer>() {{
                    setTargetType(Customer.class);
                }})
                .build();
    }


    @Bean
    public CustomerProcessor customerProcessor() {
        return new CustomerProcessor();
    }

    @Bean
    public RepositoryItemWriter<Customer> customerWritter() {
        RepositoryItemWriter<Customer> repositoryItemWriter = new RepositoryItemWriter();
        repositoryItemWriter.setRepository(customerRepository);
        repositoryItemWriter.setMethodName("save");
        return repositoryItemWriter;
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("csv-step").<Customer, Customer>chunk(10).
                reader(reader()).
                processor(customerProcessor()).
                writer(customerWritter()).
                faultTolerant().skip(NumberFormatException.class).skipLimit(4).
                taskExecutor(taskExecutor()).
                build();
    }
    @Bean
    public Job job() {
        return jobBuilderFactory.get("importCustomer").flow(step1()).end().build();
    }

    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
        taskExecutor.setConcurrencyLimit(15);
        return taskExecutor;
    }

}
