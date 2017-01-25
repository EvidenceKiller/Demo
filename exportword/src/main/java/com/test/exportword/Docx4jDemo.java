package com.test.exportword;

import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.contenttype.ContentType;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.PartName;
import org.docx4j.openpackaging.parts.WordprocessingML.AlternativeFormatInputPart;
import org.docx4j.relationships.Relationship;
import org.docx4j.wml.CTAltChunk;

import java.io.File;

public class Docx4jDemo {

    public Docx4jDemo() {
    }

    public void html2Wrod(File externalDir) throws Exception {
        String html = "<html><head><title>Import me</title></head><body><p>Hello World!</p></body></html>";

        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
        AlternativeFormatInputPart afiPart = new AlternativeFormatInputPart(new PartName("/hw.html"));
        afiPart.setBinaryData(html.getBytes());
        afiPart.setContentType(new ContentType("text/html"));
        Relationship altChunkRel = wordMLPackage.getMainDocumentPart().addTargetPart(afiPart);

// .. the bit in document body
        CTAltChunk ac = Context.getWmlObjectFactory().createCTAltChunk();
        ac.setId(altChunkRel.getId());
        wordMLPackage.getMainDocumentPart().addObject(ac);

// .. content type
        File outFile = new File(externalDir, "docx4jdemo.doc");
        wordMLPackage.getContentTypeManager().addDefaultContentType("html", "text/html");
        wordMLPackage.save(outFile);

    }

}
