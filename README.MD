#PDF Generation using JasperReports
 This is a simple tutorial that is meant to show You, how easy PDF generation can be if U decide to use Jasper Reports.

#Prerequisites
## Compiling and running sample application
1. Java 1.8
2. Maven 3.3.x
3. Favourite IDE with Lombok plugin

## Editing Jasper Reports templates
- JasperSoft Studio Community

or

- favourite Text Editor (not recommended for beginners - JasperSoft Studio provides awesome WYSIWYG editor)

#Code walk trough

#Templates
This tutorial contains couple examples, starting from really easy ones up to more complicated.

## Flat structured
In this case we will use:
- template with only one page
- data from class that does not have any nested objects (apart from standard Java types)

### Files
- FlatStructuredClass.java
- FlatStructuredTemplate.jrxml
- Application.java
- TutorialGenerationService.java

### DIY
1. Open JasperSoft Studio
2. Click ```File->New->JasperReport```
3. Choose ```Blank A4``` and click ```Next```
4. Give the file name of ```FlatStructuredTemplate.jrxml``` and click ```next```




#Why there are no tests?
You may ask - why not TDD, why there are no tests... The answer is simple - it is really hard to test if generated PDF looks OK - it must be veryfied by hooman/monkey/vegan.
