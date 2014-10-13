/*
 * Copyright 2014 adelmir.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.uft.lattes.model;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author adelmir
 */
@Entity
@Table(name = "lattes_search_infor")
public class LattesSearchInfor implements Serializable {    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Lob
    byte[] pdf;
    @Transient
    File filePdf;
    @Lob
    byte[] txt;
    @Column(name = "resultado")
    @Lob
    byte[] result;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    Date initSearch;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    Date endSearch;
    @Transient
    String words;
    @Transient
    List<LattesResult> results;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getPdf() {
        return pdf;
    }

    public void setPdf(byte[] pdf) {
        this.pdf = pdf;
    }

    public byte[] getTxt() {
        return txt;
    }

    public void setTxt(byte[] txt) {
        this.txt = txt;
    }

    public byte[] getResult() {
        return result;
    }

    public void setResult(byte[] result) {
        this.result = result;
    }

    public Date getInitSearch() {
        return initSearch;
    }

    public void setInitSearch(Date initSearch) {
        this.initSearch = initSearch;
    }

    public Date getEndSearch() {
        return endSearch;
    }

    public void setEndSearch(Date endSearch) {
        this.endSearch = endSearch;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public List<LattesResult> getResults() {
        return results;
    }

    public void setResults(List<LattesResult> results) {
        this.results = results;
    }            
    
}
