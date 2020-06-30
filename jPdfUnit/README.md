# jpdf-unit
This package provides methods to verify the correctness of pdf files. See the examples for more information

###examples

```java
public class Test{
    @Test
    public void myTest(){
        //arrange

        //act
        File pdfFile = generatePdfFile();
    
        //assert
        PdfAssert.assertThat(pdfFile).hasNumberOfPages(1);
    }
}

```

```java
public class Test{
    @Test
    public void myTest(){
        //arrange

        //act
        File pdfFile = generatePdfFile();
    
        //assert
        File referencePdfFile = Paths.get(myTest.class.getResource("/reference.pdf")).toFile();
        PdfAssert.assertThat(pdfFile).hasSameAppearanceAs(referencePdfFile);
    }
}

```