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
package com.uft.lattes.util;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author adelmir
 */
@ManagedBean
@ApplicationScoped
public class HadoopController {

    @PostConstruct
    public void init() {
        try {
            System.out.println("Iniciandooo");
            Runtime r = Runtime.getRuntime();
            r.exec("hadoop namenode -format");
            r.exec("start-all.sh").waitFor();
        } catch (IOException ex) {
            Logger.getLogger(HadoopController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(HadoopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public HadoopController() {
    }
        
}
