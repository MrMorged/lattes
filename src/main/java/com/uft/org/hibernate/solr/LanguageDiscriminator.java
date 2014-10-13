/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uft.org.hibernate.solr;

import org.hibernate.search.analyzer.Discriminator;

/**
 *
 * @author Adelmir
 */
public class LanguageDiscriminator implements Discriminator {

    @Override
    public String getAnalyzerDefinitionName(Object value, Object entity, String field) {
         if ( value == null ) {
            return null;
        }
        return (String) value;

    }
}