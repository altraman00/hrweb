package com.sunlands.hr.myutils.sceltotxt;

import java.util.List;
import java.util.Map;

public class SougouScelModel {
	private Map<String, List<String>> wordMap;  
	  
    private String name;  
    private String type;  
    private String description;  
    private String sample;  
  
    public Map<String, List<String>> getWordMap() {  
        return wordMap;  
    }  
  
    void setWordMap(Map<String, List<String>> wordMap) {  
        this.wordMap = wordMap;  
    }  
  
    public String getType() {  
        return type;  
    }  
  
    public void setType(String type) {  
        this.type = type;  
    }  
  
    public String getDescription() {  
        return description;  
    }  
  
    public void setDescription(String description) {  
        this.description = description;  
    }  
  
    public String getSample() {  
        return sample;  
    }  
  
    public void setSample(String sample) {  
        this.sample = sample;  
    }  
  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    } 
}
