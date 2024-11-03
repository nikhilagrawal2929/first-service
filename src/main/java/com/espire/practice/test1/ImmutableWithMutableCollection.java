package com.espire.practice.test1;

import java.util.ArrayList;
 import java.util.Collections;
 import java.util.List;
 
 public final class ImmutableWithMutableCollection {
 
 private final int id;
 private final List<String> mutableList;
 
 public ImmutableWithMutableCollection(int id, List<String> mutableList) {
 
 this.id = id;
 this.mutableList = new ArrayList<>(mutableList);
 }
 
 public int getId() {
 return id;
 }
 
 public List<String> getMutableList() {
 return Collections.unmodifiableList(mutableList);
 }
 
 }