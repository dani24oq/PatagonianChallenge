package patagonian;

import base.BaseTests;
import org.testng.annotations.Test;

import java.io.*;
import java.util.List;

public class PatagonianTest extends BaseTests {

    int i = 1;

    @Test
    public void patagonianTest(){

        try(BufferedWriter writing = new BufferedWriter(new FileWriter("output.txt"))){

            landingPage.agreeingCookiesPolicy();
            landingPage.clickingAboutUs();

            System.out.println("About Us URL: " + landingPage.getAboutUsUrl());
            writing.write( "About Us URL: " + landingPage.getAboutUsUrl() + "\n");

            addSpaceLines(writing);

            printingElements(landingPage.getAllUrls(),"Url", writing);
            printingElements(landingPage.getAllUrlWithImgTag(), "Url", writing);

            addSpaceLines(writing);

            i = 1;

            printingElements(landingPage.getAllBtnWithButtonTag(),"Button", writing);
            printingElements(landingPage.getAllBtnWithATag(),"Button", writing);
            printingElements(landingPage.getAllBtnWithDivTag(), "Button", writing);

            addSpaceLines(writing);

            i = 1;


            printingElements(landingPage.getInputFields(), "Input", writing);

            landingPage.clickLoginBtn();
            landingPage.logIntoThePlatform();
            landingPage.skipPhoneVerification();

        }catch (IOException e){

        }

    }

    public void addSpaceLines(BufferedWriter writing) throws IOException {
        writing.write( "----------------------------------------------------- \n");
        writing.write( "----------------------------------------------------- \n");
        System.out.println("-----------------------------------------------------");
        System.out.println("-----------------------------------------------------");
    }

    public void printingElements(List<String> input, String elementName, BufferedWriter writing) throws IOException {

        for (String element : input){
            System.out.println(elementName + "[" + i + "] : " + element);
            writing.write( elementName + "[" + i + "] : " + element + "\n");
            i++;
        }
    }

}
