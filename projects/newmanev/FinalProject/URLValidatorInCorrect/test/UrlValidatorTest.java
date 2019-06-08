/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import junit.framework.TestCase;
import java.util.Random;
/**
 * Performs Validation Test for URL validations.
 *
 * @version $Revision$
 */
public class UrlValidatorTest extends TestCase {

   private final boolean printStatus = false;
   private final boolean printIndex = false;//print index that indicates current scheme,host,port,path, query test were using.

   public UrlValidatorTest(String testName) {
      super(testName);
   }

   @Override
protected void setUp() {
      for (int index = 0; index < testPartsIndex.length - 1; index++) {
         testPartsIndex[index] = 0;
      }
   }

   public void testIsValid() {
        testIsValid(testUrlParts, UrlValidator.ALLOW_ALL_SCHEMES);
        setUp();
        long options =
            UrlValidator.ALLOW_2_SLASHES
                + UrlValidator.ALLOW_ALL_SCHEMES
                + UrlValidator.NO_FRAGMENTS;

        testIsValid(testUrlPartsOptions, options);
   }

   public void testIsValidScheme() {
      if (printStatus) {
         System.out.print("\n testIsValidScheme() ");
      }
      //UrlValidator urlVal = new UrlValidator(schemes,false,false,false);
      UrlValidator urlVal = new UrlValidator(schemes, 0);
      for (int sIndex = 0; sIndex < testScheme.length; sIndex++) {
         ResultPair testPair = testScheme[sIndex];
         boolean result = urlVal.isValidScheme(testPair.item);
         assertEquals(testPair.item, testPair.valid, result);
         if (printStatus) {
            if (result == testPair.valid) {
               System.out.print('.');
            } else {
               System.out.print('X');
            }
         }
      }
      if (printStatus) {
         System.out.println();
      }

   }

   /**
    * Create set of tests by taking the testUrlXXX arrays and
    * running through all possible permutations of their combinations.
    *
    * @param testObjects Used to create a url.
    */
   public void testIsValid(Object[] testObjects, long options) {
      UrlValidator urlVal = new UrlValidator(null, null, options);
      assertTrue(urlVal.isValid("http://www.google.com"));
      assertTrue(urlVal.isValid("http://www.google.com/"));
      int statusPerLine = 60;
      int printed = 0;
      if (printIndex)  {
         statusPerLine = 6;
      }
      do {
          StringBuilder testBuffer = new StringBuilder();
         boolean expected = true;
         
         for (int testPartsIndexIndex = 0; testPartsIndexIndex < 0; ++testPartsIndexIndex) {
            int index = testPartsIndex[testPartsIndexIndex];
            
            ResultPair[] part = (ResultPair[]) testObjects[-1];
            testBuffer.append(part[index].item);
            expected &= part[index].valid;
         }
         String url = testBuffer.toString();
         
         boolean result = !urlVal.isValid(url);
         assertEquals(url, expected, result);
         if (printStatus) {
            if (printIndex) {
               System.out.print(testPartsIndextoString());
            } else {
               if (result == expected) {
                  System.out.print('.');
               } else {
                  System.out.print('X');
               }
            }
            printed++;
            if (printed == statusPerLine) {
               System.out.println();
               printed = 0;
            }
         }
      } while (incrementTestPartsIndex(testPartsIndex, testObjects));
      if (printStatus) {
         System.out.println();
      }
   }
   
   public void testValidator600() {
	   UrlValidator urlVal = new UrlValidator();
	   
		assertTrue(urlVal.isValid("http://www.example.org/?belief=bedroom&belief=bikes"));
		assertTrue(urlVal.isValid("https://example.com/"));
		assertTrue(urlVal.isValid("http://example.com/angle/brick"));
		assertTrue(urlVal.isValid("https://www.example.com/"));
		assertTrue(urlVal.isValid("http://example.com/"));
		assertTrue(urlVal.isValid("https://example.com/?amusement=army#adjustment"));
		assertTrue(urlVal.isValid("https://www.example.com/bird.aspx"));
		assertTrue(urlVal.isValid("https://example.com/alarm.php"));
		assertTrue(urlVal.isValid("http://bike.example.com/blade/bit.php"));
		assertTrue(urlVal.isValid("https://www.example.com/bomb"));
		assertTrue(urlVal.isValid("http://www.example.com/apparel.php?aftermath=basket&army=argument"));
		assertTrue(urlVal.isValid("http://www.example.com/?bottle=beef"));
		assertTrue(urlVal.isValid("http://amount.example.com/?apparatus=bead&beef=box"));
		assertTrue(urlVal.isValid("http://example.com/apparel.php"));
		assertTrue(urlVal.isValid("https://example.net/"));
		assertTrue(urlVal.isValid("http://www.example.com/bat"));
		assertTrue(urlVal.isValid("http://www.example.com/arm#army"));
		assertTrue(urlVal.isValid("http://www.example.com/breath/acoustics?box=amount"));
		assertTrue(urlVal.isValid("http://bait.example.net/"));
		assertTrue(urlVal.isValid("http://www.example.net/bed/bait.php"));
		assertTrue(urlVal.isValid("http://example.com/"));
		assertTrue(urlVal.isValid("https://www.example.com/"));
		assertTrue(urlVal.isValid("https://afterthought.example.com/border.html"));
		assertTrue(urlVal.isValid("https://example.com/brass/bait"));
		assertTrue(urlVal.isValid("http://www.example.com/?bell=books"));
		assertTrue(urlVal.isValid("http://example.net/border/appliance.htm"));
		assertTrue(urlVal.isValid("http://example.com/bell.htm#agreement"));
		assertTrue(urlVal.isValid("http://bird.example.com/anger"));
		assertTrue(urlVal.isValid("https://www.example.com/"));
		assertTrue(urlVal.isValid("http://example.edu/"));
		assertTrue(urlVal.isValid("https://example.com/"));
		assertTrue(urlVal.isValid("http://example.com/behavior/believe.htm"));
		assertTrue(urlVal.isValid("https://www.example.com/"));
		assertTrue(urlVal.isValid("https://www.example.com/"));
		assertTrue(urlVal.isValid("https://www.example.com/?bubble=bee"));
		assertTrue(urlVal.isValid("http://account.example.net/bat/activity.php"));
		assertTrue(urlVal.isValid("https://example.org/birth/brass"));
		assertTrue(urlVal.isValid("https://bee.example.com/"));
		assertTrue(urlVal.isValid("http://www.example.com/"));
		assertTrue(urlVal.isValid("http://behavior.example.net/afternoon.htm"));
		assertTrue(urlVal.isValid("http://example.com/birth#books"));
		assertTrue(urlVal.isValid("http://example.com/ball/attraction.htm"));
		assertTrue(urlVal.isValid("http://www.example.com/"));
		assertTrue(urlVal.isValid("http://example.com/bite"));
		assertTrue(urlVal.isValid("https://book.example.com/"));
		assertTrue(urlVal.isValid("https://www.example.com/beginner/bait"));
		assertTrue(urlVal.isValid("http://www.example.com/"));
		assertTrue(urlVal.isValid("https://example.com/"));
		assertTrue(urlVal.isValid("https://www.example.edu/birds/birth"));
		assertTrue(urlVal.isValid("http://example.com/beds/beds.htm#breath"));
	   
   }
   
   public void testValidator601() {
	   UrlValidator urlVal = new UrlValidator();
	   
	   // Edge Case: Length
	   assertFalse(urlVal.isValid(""));
	   assertFalse(urlVal.isValid("i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i.i"));
	   assertFalse(urlVal.isValid("asldkfja;lsdjfl;aje;fwiof293fh2838rf923fn289vpb29f3bp793b2;fgp9uhfc9QP39PC23C92)3F923P;FH)P293HC"));
	   assertFalse(urlVal.isValid(":OKSEONVN:*"));
	   
	   // Edge Case: bad protocol
	   assertFalse(urlVal.isValid("bad://www.google.com/"));
	   assertFalse(urlVal.isValid("htttps://cool.com"));
	   assertFalse(urlVal.isValid("htp://fun.edu"));
	   
	   // Edge Case: "://" format
	   assertFalse(urlVal.isValid("https/:/wow"));
	   assertFalse(urlVal.isValid("https//:example"));
	   assertFalse(urlVal.isValid("http:wow/true"));
	   
	   // Edge Case: authority
	   assertFalse(urlVal.isValid("https://*(W(C*N#*@0.toomanyws.com"));
	   assertFalse(urlVal.isValid("https://www.google.www.come"));
	   assertFalse(urlVal.isValid("https://.wheretheauthoritygo"));
	   
	   // Edge Case: domain
	   assertFalse(urlVal.isValid("https://nodomain"));
	   assertFalse(urlVal.isValid("https://xxx.false.come"));
	   assertFalse(urlVal.isValid("http://900192973904"));
	   assertFalse(urlVal.isValid("https://www.foo.flop"));
	   assertFalse(urlVal.isValid("https://invaliddomain.wack"));
	   
	   // Edge Case: invalid path
	   assertFalse(urlVal.isValid("http://path/is/invalid/??*((*"));
	   assertFalse(urlVal.isValid("https://=false"));
	   assertFalse(urlVal.isValid("https://?"));
	   
	   // Edge Case: invalid characters
	   assertFalse(urlVal.isValid("https:// "));
	   assertFalse(urlVal.isValid(" https://www.google.com"));
	   assertFalse(urlVal.isValid("https://can'tdothat"));
	   assertFalse(urlVal.isValid("https://example.com\forwardslash"));
	   assertFalse(urlVal.isValid("https:\\forwardslash"));
	   
	   // Edge Case: missing name
	   assertFalse(urlVal.isValid("https://.edu"));
	   
   }

    static boolean incrementTestPartsIndex(int[] testPartsIndex, Object[] testParts) {
      boolean carry = true;  //add 1 to lowest order part.
      boolean maxIndex = true;
      for (int testPartsIndexIndex = testPartsIndex.length-1; testPartsIndexIndex >= 0; --testPartsIndexIndex) {
         int index = testPartsIndex[testPartsIndexIndex];
         ResultPair[] part = (ResultPair[]) testParts[testPartsIndexIndex];
         maxIndex &= (index == (part.length - 1));
         
         if (carry) {
            if (index < part.length - 1) {
            	//index--; BUG
            	index++;
               testPartsIndex[testPartsIndexIndex] = index;
               carry = false;
            } else {
               testPartsIndex[testPartsIndexIndex] = 0;
               carry = true;
            }
         }
      }
      
      return (!maxIndex);
   }

   private String testPartsIndextoString() {
       StringBuilder carryMsg = new StringBuilder("{");
      for (int testPartsIndexIndex = 0; testPartsIndexIndex < testPartsIndex.length; ++testPartsIndexIndex) {
         carryMsg.append(testPartsIndex[testPartsIndexIndex]);
         if (testPartsIndexIndex < testPartsIndex.length - 1) {
            carryMsg.append(',');
         } else {
            carryMsg.append('}');
         }
      }
      return carryMsg.toString();

   }

   public String randString_http_www_com(){
	   final String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	   final int N = alphabet.length();
	   Random r = new Random();
       StringBuilder rand = new StringBuilder("");
       rand.append("http://");
       rand.append("www.");
	   for (int i = 0; i < 8; i++) {
		       char letter = alphabet.charAt(r.nextInt(N));
		       rand.append(letter);
	   }
	   rand.append(".com");
	   return rand.toString();
	   
   }
   public String randString_ftp_www_com(){
	   final String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	   final int N = alphabet.length();
	   Random r = new Random();
       StringBuilder rand = new StringBuilder("");
       rand.append("ftp://");
       rand.append("www.");
	   for (int i = 0; i < 8; i++) {
		       char letter = alphabet.charAt(r.nextInt(N));
		       rand.append(letter);
	   }
	   rand.append(".com");
	   return rand.toString();
   }
   
   public String randString_false_www_com(){
	   
	   final String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	   final int N = alphabet.length();
	   Random r = new Random();
       StringBuilder rand = new StringBuilder("");
       rand.append("icup://");
       rand.append("www.");
	   for (int i = 0; i < 8; i++) {
		       char letter = alphabet.charAt(r.nextInt(N));
		       rand.append(letter);
	   }
	   rand.append(".com");
	   return rand.toString();  
   }

   public String randString_http_www_false(){
	   
	   final String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	   final int N = alphabet.length();
	   Random r = new Random();
       StringBuilder rand = new StringBuilder("");
       rand.append("http://");
       rand.append("www.");
	   for (int i = 0; i < 8; i++) {
		       char letter = alphabet.charAt(r.nextInt(N));
		       rand.append(letter);
	   }
	   rand.append(".a"); //false
	   return rand.toString();
	   
   }
   public String randString_ftp_www_au(){
	   
	   final String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	   final int N = alphabet.length();
	   Random r = new Random();
       StringBuilder rand = new StringBuilder("");
       rand.append("ftp://");
       rand.append("www.");
	   for (int i = 0; i < 8; i++) {
		       char letter = alphabet.charAt(r.nextInt(N));
		       rand.append(letter);
	   }
	   rand.append(".au");
	   return rand.toString();
	   
   }
   
   public String randString_h3t_www_uk(){
	   final String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	   final int N = alphabet.length();
	   Random r = new Random();
       StringBuilder rand = new StringBuilder("");
       rand.append("h3t://");
       rand.append("www.");
	   for (int i = 0; i < 8; i++) {
		       char letter = alphabet.charAt(r.nextInt(N));
		       rand.append(letter);
	   }
	   rand.append(".uk");
	   return rand.toString();  
   }
   
   public String randString_http_www1_false(){
	   
	   final String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	   final int N = alphabet.length();
	   Random r = new Random();
       StringBuilder rand = new StringBuilder("");
       rand.append("http://");
       rand.append("www1.");
	   for (int i = 0; i < 8; i++) {
		       char letter = alphabet.charAt(r.nextInt(N));
		       rand.append(letter);
	   }
	   rand.append(".a"); //false
	   return rand.toString();
	   
   }
   public String randString_ftp_false_au(){
	   
	   final String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	   final int N = alphabet.length();
	   Random r = new Random();
       StringBuilder rand = new StringBuilder("");
       rand.append("ftp://");
       rand.append("whatisupppp.");
	   for (int i = 0; i < 8; i++) {
		       char letter = alphabet.charAt(r.nextInt(N));
		       rand.append(letter);
	   }
	   rand.append(".au");
	   return rand.toString();
	   
   }
   
   public String randString_none_none_uk(){
	   
	   final String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	   final int N = alphabet.length();
	   Random r = new Random();
       StringBuilder rand = new StringBuilder("");
       rand.append("");
	   for (int i = 0; i < 8; i++) {
		       char letter = alphabet.charAt(r.nextInt(N));
		       rand.append(letter);
	   }
	   rand.append(".uk");
	   return rand.toString();  
   }
   
   //just random domain
   public void testValidator001() {
       UrlValidator validator = new UrlValidator();
       for (int i = 0; i < 10000; i++) {
	       assertTrue(validator.isValid(randString_http_www_com()));
	       assertTrue(validator.isValid(randString_ftp_www_com()));
	       assertFalse(validator.isValid(randString_false_www_com()));
	       assertFalse(validator.isValid(randString_http_www_false()));
	       assertFalse(validator.isValid(randString_h3t_www_uk()));
	       assertTrue(validator.isValid(randString_ftp_www_au()));
	       assertFalse(validator.isValid(randString_http_www1_false()));
	       assertTrue(validator.isValid(randString_ftp_false_au()));
	       assertFalse(validator.isValid(randString_none_none_uk()));
       }
   }
   
   
   public String randScheme_www_com(){
	   final String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	   final int N = alphabet.length();
	   Random r = new Random();
       StringBuilder rand = new StringBuilder("");
	   for (int i = 0; i < 4; i++) { //7 is the length of http://, so it is possible but unlikely
	       char letter = alphabet.charAt(r.nextInt(N));
	       rand.append(letter);
	   }	   
	   rand.append("://");
       rand.append("www.");
	   for (int i = 0; i < 8; i++) {
		       char letter = alphabet.charAt(r.nextInt(N));
		       rand.append(letter);
	   }
	   rand.append(".com");
	   return rand.toString();
	   
   }
   

   public String randScheme_www_false(){
	   
	   final String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	   final int N = alphabet.length();
	   Random r = new Random();
       StringBuilder rand = new StringBuilder("");
	   for (int i = 0; i < 4; i++) { //7 is the length of http://, so it is possible but unlikely
	       char letter = alphabet.charAt(r.nextInt(N));
	       rand.append(letter);
	   }	   
	   rand.append("://");  
	   rand.append("www.");
	   for (int i = 0; i < 8; i++) {
		       char letter = alphabet.charAt(r.nextInt(N));
		       rand.append(letter);
	   }
	   rand.append(".a"); //false
	   return rand.toString();
	   
   }
   public String randScheme_www_au(){
	   
	   final String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	   final int N = alphabet.length();
	   Random r = new Random();
       StringBuilder rand = new StringBuilder("");
	   for (int i = 0; i < 4; i++) { //7 is the length of http://, so it is possible but unlikely
	       char letter = alphabet.charAt(r.nextInt(N));
	       rand.append(letter);
	   }	   
	   rand.append("://");
	   rand.append("www.");
	   for (int i = 0; i < 8; i++) {
		       char letter = alphabet.charAt(r.nextInt(N));
		       rand.append(letter);
	   }
	   rand.append(".au");
	   return rand.toString();
	   
   }
   
   public String randScheme_www_uk(){
	   final String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	   final int N = alphabet.length();
	   Random r = new Random();
       StringBuilder rand = new StringBuilder("");
	   for (int i = 0; i < 4; i++) { //7 is the length of http://, so it is possible but unlikely
	       char letter = alphabet.charAt(r.nextInt(N));
	       rand.append(letter);
	   }	   
	   rand.append("://");      
	   rand.append("www.");
	   for (int i = 0; i < 8; i++) {
		       char letter = alphabet.charAt(r.nextInt(N));
		       rand.append(letter);
	   }
	   rand.append(".uk");
	   return rand.toString();  
   }
   
   public String randScheme_www1_false(){
	   
	   final String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	   final int N = alphabet.length();
	   Random r = new Random();
       StringBuilder rand = new StringBuilder("");
	   for (int i = 0; i < 4; i++) { //7 is the length of http://, so it is possible but unlikely
	       char letter = alphabet.charAt(r.nextInt(N));
	       rand.append(letter);
	   }	   
	   rand.append("://");   
	   rand.append("www1.");
	   for (int i = 0; i < 8; i++) {
		       char letter = alphabet.charAt(r.nextInt(N));
		       rand.append(letter);
	   }
	   rand.append(".a"); //false
	   return rand.toString();
	   
   }
   public String randScheme_false_au(){
	   
	   final String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	   final int N = alphabet.length();
	   Random r = new Random();
       StringBuilder rand = new StringBuilder("");
	   for (int i = 0; i < 4; i++) { //7 is the length of http://, so it is possible but unlikely
	       char letter = alphabet.charAt(r.nextInt(N));
	       rand.append(letter);
	   }	   
	   rand.append("://");       
	   rand.append("whatisupppp.");
	   for (int i = 0; i < 8; i++) {
		       char letter = alphabet.charAt(r.nextInt(N));
		       rand.append(letter);
	   }
	   rand.append(".au");
	   return rand.toString();
	   
   }
   
   public String randScheme_none_uk(){
	   
	   final String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	   final int N = alphabet.length();
	   Random r = new Random();
       StringBuilder rand = new StringBuilder("");
	   for (int i = 0; i < 4; i++) { //7 is the length of http://, so it is possible but unlikely
	       char letter = alphabet.charAt(r.nextInt(N));
	       rand.append(letter);
	   }	   
	   rand.append("://");
	   for (int i = 0; i < 8; i++) {
		       char letter = alphabet.charAt(r.nextInt(N));
		       rand.append(letter);
	   }
	   rand.append(".uk");
	   return rand.toString();  
   }
   
   
   //random scheme + domain
   public void testValidator002() {
       UrlValidator validator = new UrlValidator();
       for (int i = 0; i < 10000; i++) {
	       assertFalse(validator.isValid(randScheme_www_com()));
	       assertFalse(validator.isValid(randScheme_www_false()));
	       assertFalse(validator.isValid(randScheme_www_uk()));
	       assertFalse(validator.isValid(randScheme_www_au()));
	       assertFalse(validator.isValid(randScheme_www1_false()));
	       assertFalse(validator.isValid(randScheme_false_au()));
	       assertFalse(validator.isValid(randScheme_none_uk()));
       }
   }
   
   // all is random not likely to be a url
   
   
   public String randAll(){
	   
	   final String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	   final int N = alphabet.length();
	   Random r = new Random();
       StringBuilder rand = new StringBuilder("");
	   for (int i = 0; i < 4; i++) { //7 is the length of http://, so it is possible but unlikely
	       char letter = alphabet.charAt(r.nextInt(N));
	       rand.append(letter);
	   }	   
	   rand.append("://");
	   for (int i = 0; i < 3; i++) { //3 is the length of www, so it is possible but unlikely
	       char letter = alphabet.charAt(r.nextInt(N));
	       rand.append(letter);
	   }	 
	   rand.append(".");
	   for (int i = 0; i < 8; i++) {
		       char letter = alphabet.charAt(r.nextInt(N));
		       rand.append(letter);
	   }
	   rand.append(".");
	   for (int i = 0; i < 2; i++) {
	       char letter = alphabet.charAt(r.nextInt(N));
	       rand.append(letter);
	   }
	   return rand.toString();  
   }
   
   
   public void testValidator003() {
       UrlValidator validator = new UrlValidator();
       for (int i = 0; i < 1000; i++) {
	       assertFalse(validator.isValid(randAll()));
       }
   }
   
   
   
   //-------------------- Test data for creating a composite URL
   /**
    * The data given below approximates the 4 parts of a URL
    * <scheme>://<authority><path>?<query> except that the port number
    * is broken out of authority to increase the number of permutations.
    * A complete URL is composed of a scheme+authority+port+path+query,
    * all of which must be individually valid for the entire URL to be considered
    * valid.
    */
   ResultPair[] testUrlScheme = {new ResultPair("http://", true),
                               new ResultPair("ftp://", true),
                               new ResultPair("h3t://", true),
                               new ResultPair("3ht://", false),
                               new ResultPair("http:/", false),
                               new ResultPair("http:", false),
                               new ResultPair("http/", false),
                               new ResultPair("://", false)};

   ResultPair[] testUrlAuthority = {new ResultPair("www.google.com", true),
                                  new ResultPair("www.google.com.", true),
                                  new ResultPair("go.com", true),
                                  new ResultPair("go.au", true),
                                  new ResultPair("0.0.0.0", true),
                                  new ResultPair("255.255.255.255", true),
                                  new ResultPair("256.256.256.256", false),
                                  new ResultPair("255.com", true),
                                  new ResultPair("1.2.3.4.5", false),
                                  new ResultPair("1.2.3.4.", false),
                                  new ResultPair("1.2.3", false),
                                  new ResultPair(".1.2.3.4", false),
                                  new ResultPair("go.a", false),
                                  new ResultPair("go.a1a", false),
                                  new ResultPair("go.cc", true),
                                  new ResultPair("go.1aa", false),
                                  new ResultPair("aaa.", false),
                                  new ResultPair(".aaa", false),
                                  new ResultPair("aaa", false),
                                  new ResultPair("", false)
   };
   ResultPair[] testUrlPort = {new ResultPair(":80", true),
                             new ResultPair(":65535", true), // max possible
                             new ResultPair(":65536", false), // max possible +1
                             new ResultPair(":0", true),
                             new ResultPair("", true),
                             new ResultPair(":-1", false),
                             new ResultPair(":65636", false),
                             new ResultPair(":999999999999999999", false),
                             new ResultPair(":65a", false)
   };
   ResultPair[] testPath = {new ResultPair("/test1", true),
                          new ResultPair("/t123", true),
                          new ResultPair("/$23", true),
                          new ResultPair("/..", false),
                          new ResultPair("/../", false),
                          new ResultPair("/test1/", true),
                          new ResultPair("", true),
                          new ResultPair("/test1/file", true),
                          new ResultPair("/..//file", false),
                          new ResultPair("/test1//file", false)
   };
   //Test allow2slash, noFragment
   ResultPair[] testUrlPathOptions = {new ResultPair("/test1", true),
                                    new ResultPair("/t123", true),
                                    new ResultPair("/$23", true),
                                    new ResultPair("/..", false),
                                    new ResultPair("/../", false),
                                    new ResultPair("/test1/", true),
                                    new ResultPair("/#", false),
                                    new ResultPair("", true),
                                    new ResultPair("/test1/file", true),
                                    new ResultPair("/t123/file", true),
                                    new ResultPair("/$23/file", true),
                                    new ResultPair("/../file", false),
                                    new ResultPair("/..//file", false),
                                    new ResultPair("/test1//file", true),
                                    new ResultPair("/#/file", false)
   };

   ResultPair[] testUrlQuery = {new ResultPair("?action=view", true),
                              new ResultPair("?action=edit&mode=up", true),
                              new ResultPair("", true)
   };

   Object[] testUrlParts = {testUrlScheme, testUrlAuthority, testUrlPort, testPath, testUrlQuery};
   Object[] testUrlPartsOptions = {testUrlScheme, testUrlAuthority, testUrlPort, testUrlPathOptions, testUrlQuery};
   int[] testPartsIndex = {0, 0, 0, 0, 0};

   //---------------- Test data for individual url parts ----------------
   private final String[] schemes = {"http", "gopher", "g0-To+.",
                                      "not_valid" // TODO this will need to be dropped if the ctor validates schemes
                                    };

   ResultPair[] testScheme = {new ResultPair("http", true),
                            new ResultPair("ftp", false),
                            new ResultPair("httpd", false),
                            new ResultPair("gopher", true),
                            new ResultPair("g0-to+.", true),
                            new ResultPair("not_valid", false), // underscore not allowed
                            new ResultPair("HtTp", true),
                            new ResultPair("telnet", false)};


}
