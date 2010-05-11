package org.snapimpact.dispatch

/**
 * Created by IntelliJ IDEA.
 * User: flipper
 * Date: May 5, 2010
 * Time: 9:24:15 PM
 * To change this template use File | Settings | File Templates.
 */


import _root_.org.specs._
import _root_.org.specs.runner._
import _root_.org.specs.Sugar._

import net.liftweb.http.testing._
import net.liftweb.common._
import net.liftweb.util.Helpers._
import net.liftweb.json._
import JsonParser._
import JsonAST._
import specification.Expectable
import _root_.org.specs.execute._
import org.joda.time._
import org.joda.time.format._
import scala.xml.XML


class AnimalsAndTest extends Runner(new AnimalsAndSpec ) with JUnit with Console
//
class AnimalsAndSpec extends Specification with ApiSubmitTester with TestKit
{
  def baseUrl = "http://localhost:8989"
  RunWebApp.start()

  "AnimalAnd loaded api"should
  {

    // Upload the sample file, that holds the test data
    "Accept valid xml upload" in {
       post("/api/upload?key=somekey", XML.loadFile("src/test/resources/AnimalsAndData.xml")) match {
         case r: HttpResponse =>
           r.code must_== 200
         case x => x must fail
       }
     }

    "return exact count of events" in {  
  org.snapimpact.util.SkipHandler.pendingUntilFixed{
      val ret = submitApiRequest( "output" -> "json", "key" -> "UnitTest", 
      "q" -> "game" )
      
      val count = 250;
      ( ret.items.length == count ) must_== true
      
      for( item <- ret.items ){
      item must notBe( null )}
}}



"return exact count of events when wolf and bunny meet" in {
  org.snapimpact.util.SkipHandler.pendingUntilFixed{
      val ret = submitApiRequest( "output" -> "json", "key" -> "UnitTest", 
      "q" -> "wolf +bunny")
      
      val count = 1;
      //System.out.println( "* Expected val=" + count + ", was=" + ret.items.length + "when wolf and bunny meet")
      ( ret.items.length == count ) must_== true
      
      for( item <- ret.items ){
      item must notBe( null )}
}}

"return exact count of events when wolf and turtle meet" in {
          val ret = submitApiRequest( "output" -> "json", "key" -> "UnitTest",
          "q" -> "wolf +turtle")

          val count = 2;
          //System.out.println( "* Expected val=" + count + ", was=" + ret.items.length )
          ( ret.items.length == count ) must_== true

          for( item <- ret.items ){
          item must notBe( null )}
    }

    "return exact count of events when turtle and bunny meet" in {
              org.snapimpact.util.SkipHandler.pendingUntilFixed{
                  val ret = submitApiRequest( "output" -> "json", "key" -> "UnitTest",
                  "q" -> "turtle +bunny")

                  val count = 2;
                  //System.out.println( "* Expected val=" + count + ", was=" + ret.items.length + "turtle and bunny meet")
                  ( ret.items.length == count ) must_== true

                  for( item <- ret.items ){
                  item must notBe( null )}
            }}

    "return exact count of events when bunny and turtle meet" in {

                  val ret = submitApiRequest( "output" -> "json", "key" -> "UnitTest",
                  "q" -> "bunny +turtle")

                  val count = 2;
                  //System.out.println( "* Expected val=" + count + ", was=" + ret.items.length + "bunny and turtle meet")
                  ( ret.items.length == count ) must_== true

                  for( item <- ret.items ){
                  item must notBe( null )}
            }


    "return exact count of events when bunny is hopping alone" in {
                  val ret = submitApiRequest( "output" -> "json", "key" -> "UnitTest",
                  "q" -> "bunny")

                  val count = 4;
                  //System.out.println( "* Expected val=" + count + ", was=" + ret.items.length )
                  ( ret.items.length == count ) must_== true

                  for( item <- ret.items ){
                  item must notBe( null )}
            }


    "return exact count of events when wolf has imaginary friend" in {
                  val ret = submitApiRequest( "output" -> "json", "key" -> "UnitTest",
                  "q" -> "bunny +imagination")

                  val count = 0;
                  //System.out.println( "* Expected val=" + count + ", was=" + ret.items.length )
                  ( ret.items.length == count ) must_== true

                  for( item <- ret.items ){
                  item must notBe( null )}
            }



        // Done
        //RunWebApp.stop()

    }  //  api should

}   // ApiSampleDataSpec
