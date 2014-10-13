///*
// * Copyright 2014 adelmir.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package net.ensode.glassfishlattes;
//
//import java.util.Queue;
//import javax.annotation.Resource;
//import org.hibernate.Session;
//import org.jgroups.protocols.STOMP.Connection;
//import org.postgresql.core.ConnectionFactory;
//import 
//
//
//
///**
// *
// * @author adelmir
// */
//
//
//public class MessageSender
//{
//  @Resource(mappedName = "jms/GlassFishBookConnectionFactory")
//  private static ConnectionFactory connectionFactory;
//  @Resource(mappedName = "jms/GlassFishBookQueue")
//  private static Queue queue;
//
//  public void produceMessages()
//  {
//    MessageProducer messageProducer;
//    TextMessage textMessage;
//    try
//    {
//      Connection connection = connectionFactory.createConnection();
//      Session session = connection.createSession(false,
//        Session.AUTO_ACKNOWLEDGE);
//      messageProducer = session.createProducer(queue);
//      textMessage = session.createTextMessage();
//
//      textMessage.setText("Testing, 1, 2, 3. Can you hear me?");
//      System.out.println("Sending the following message: "
//        + textMessage.getText());
//      messageProducer.send(textMessage);
//
//      textMessage.setText("Do you copy?");
//      System.out.println("Sending the following message: "
//        + textMessage.getText());
//      messageProducer.send(textMessage);
//
//      textMessage.setText("Good bye!");
//      System.out.println("Sending the following message: "
//        + textMessage.getText());
//      messageProducer.send(textMessage);
//
//      messageProducer.close();
//      session.close();
//      connection.close();
//    }
//    catch (JMSException e)
//    {
//      e.printStackTrace();
//    }
//  }
//  public static void main(String[] args)
//  {
//    new MessageSender().produceMessages();
//  }
//}
