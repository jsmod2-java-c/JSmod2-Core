/*Culesberry technolegy Co. Ltd. (c) 2019-2020
 * 
 * Stating that the software,the software belongs Gulesberry
 * noyark open source group,noyark has all the power to interpret
 * and copyright information for the software prohibit organizations
 * and individuals conduct their business practices and illegal practices,
 * projects of: magiclu,Chinese name *Changcun Lu*.The software has nothing
 * to do with current politics,free software is the purpose of noyark
 * 
 * noyark-system info:
 * 	****************************************************
 * 											www.noyark.net
 *		 ****************************************************
 * 
 */
package net.noyark.scpslserver.jsmod2.utils.config;

import net.noyark.oaml.tree.Document;
import net.noyark.scpslserver.jsmod2.utils.Utils;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This configuration file class simplifies the
 * parsing and writing operations of markup language,concealing
 * node objects,merging parsing and writing,but the
 * underlying layer is still a node tree,but external
 * users can learn the data without knowing the node
 * tree structure,but here Nodes,paths are unique,that
 * is ,elements with the same name are not allowed at
 * the same level.
 * @author magiclu550
 * @since oaml 003
 * @since JDK1.8
 *
 */
public abstract class Config implements IConfig{

   //读取
   protected InputStream in;
   //写入
   protected OutputStream out;

   protected String fileName;


   public Config(String fileName,boolean getClass){
      if(getClass){
         this.fileName = Utils.getClassFileName(fileName);
      }else{
         this.fileName = fileName;
      }
   }



}

