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

Steps:
- define data classes
- define fields with types at a top of Jasper Template
- use values (for example in TextField) from data source field with ```$F{FIELD_NAME}```

## Nested objects
TODO

## Lists inside root object
In this case we will use:
- template with only one page
- data from class that does have lists of object of different types defined

Steps:
- define data classes
- define fields of nested object in subDataset tag at a top of Jasper Template, name of subDataset does not need to match name of a field from data class, this value is matched differently
- define fields of root object with types below subDataset tags 
- use values from root object (for example in TextField) from data source field with ```$F{FIELD_NAME}```
- use values from nested object by defining ```datasetRun``` withing ```jr:list```

### Printing values from list
In order to print values from list, You need to use component that supports such behaviour, for example ```jr:list```
```xml
<!-- This needs to be nested inside componentElement -->
<componentElement>
                <!-- Use reportElement to position it within document -->
				<reportElement positionType="Float" stretchType="RelativeToTallestObject" isPrintRepeatedValues="false" x="0" y="110" width="554" height="25" isRemoveLineWhenBlank="true" uuid="5914e6ae-75ab-408b-8481-37cc8b9aca77">
					<property name="com.jaspersoft.studio.unit.y" value="pixel"/>
				</reportElement>
				<!-- Define list component -->
				<jr:list xmlns:jr="http://jasperreports.sourceforge.net/jasperreports/components" xsi:schemaLocation="http://jasperreports.sourceforge.net/jasperreports/components http://jasperreports.sourceforge.net/xsd/components.xsd" printOrder="Horizontal">
				    <!-- Define dataset run using name of the dataset defined at top of the report -->
					<datasetRun subDataset="nestedElementsDataSet" uuid="2002271b-6392-4900-9f37-b779ff0f6226">
					    <!-- Use dataSourceExpression to generate new JsonDataSource from field in original JsonDataSource passed from Java -->
						<dataSourceExpression><![CDATA[((net.sf.jasperreports.engine.data.JsonDataSource)$P{REPORT_DATA_SOURCE}).subDataSource("nestedElements")]]></dataSourceExpression>
					</datasetRun>
					<jr:listContents height="25" width="554">
						<!-- Content with expressions that will evaluate using regular syntax, like ${FIELD_NAME} -->
					</jr:listContents>
				</jr:list>
			</componentElement>
```

## Lists inside lists
TODO

## Custom font embedded in PDF
TODO



#Why there are no tests?
You may ask - why not TDD, why there are no tests... The answer is simple - it is really hard to test if generated PDF looks OK - it must be veryfied by hooman/monkey/vegan.
