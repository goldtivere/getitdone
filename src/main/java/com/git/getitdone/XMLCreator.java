/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.getitdone;

/**
 *
 * @author Gold
 */
public class XMLCreator {

    public void xmlCreate(String content) {
        Element root = new Element("Response");
        Document doc = new Document();

        Element child1 = new Element("Say");
        child1.setAttribute("voice", "alice");
        child1.addContent(content);

        root.addContent(child1);

        doc.setRootElement(root);

        XMLOutputter outter = new XMLOutputter();
        outter.setFormat(Format.getPrettyFormat());
        outter.output(doc, new FileWriter(new File("c:/myxml.xml")));
        System.out.println("Done");
    }
}
