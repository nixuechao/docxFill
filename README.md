# 1.说明
docxFill 可以很方便灵活的填充word模板,特别是当需要填充的部分格式较为复杂时,会显得很惬意
# 2.使用
## 1.配置word模板
在word中,需要替换的地方使用 $param$ 标出,param为变量名称,如果word中原本就需要用到
$字符,可以在WordDocument 类中配置为其它特殊字符
```java
private String label ="$";
```
## 2.构造替换类
任意构造一个java类,所有字段的变量名对应word模板中的变量名,并为它们提供get/set方法
```java
    @StrikeThrough
    @FontFamily("黑体")
    @FontSize(35)
    @TextBreak
    private String name;

    @Color("#FF0000")
    @FontSize(30)
    @TextBreak
    private String description;
```
第一个变量替换模板中的$name$ 并设置格式为带有删除线的黑体35号字体,并换行
第二个变量替换模板中的$description$ 并设置格式为 30号字体颜色为红色,并换行
## 3.一个变量需要有多种格式
有的时候模板中的一个变量可能需要被替换成好几段文字,并且格式不尽相同,如下 模板中$remark$ 变量若需要多种格式,需要构造一个Target,$remark$将被替换为下面4个部分,且每个部分的格式都不一样
```java
//替换的类
public class Template {

    @StrikeThrough
    @FontFamily("黑体")
    @FontSize(35)
    @TextBreak
    private String name;

    @Color("#FF0000")
    @FontSize(30)
    @TextBreak
    private String description;
	//对应多种格式的$remark$
    private Target remark;
    //...省略get/set
    }

//使用的类
public static void main(String[] args) throws Exception {
        Template template = new Template();
        template.setName("这是姓名");
        template.setDescription("这是说明");
		//替换$remark$
        template.setRemark(Target.builder()
                .withText("第一部分").beforeTab().afterReturn()
                .and()
                .withText("第二部分").color("#FF0000").beforeTab().afterReturn()
                .and()
                .withText("第三部分").bold().beforeTab()
                .and().withText("第四部分").upTag().build());

        //可以通过输入流或是文件路径构造
//        WordDocument wordDocument=new WordDocument(template, "D:/aaa.docx");
        WordDocument wordDocument=new WordDocument(template, Test.class.getResourceAsStream("/aaa.docx"));
        wordDocument.write(new FileOutputStream(new File("D:/bbb.docx")));
    }

```

# 3.安装
docxFill需要依赖 poi-ooxml 3.17以及更高版本
```xml
      <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi-ooxml</artifactId>
            <version>3.17</version>
        </dependency>
```
# 4.方法说明
在字段上支持的注解,以及构造Target变量时支持的方法如下

| 注解/方法                              | 说明                    |
| -------------------------------------- | ----------------------- |
| bold()                                 | 加粗                    |
| color(String color)                    | 颜色,十六进制 如#8B0000 |
| doubleStrikethrough()                  | 双删除线                |
| embossed()                             | 浮雕字体                |
| fontFamily(String fontFamily)          | 字体                    |
| fontSize(int fontSize)                 | 字号                    |
| imprinted()                            | 印迹（悬浮阴影）        |
| italic()                               | 斜体                    |
| shadow()                               | 阴影                    |
| strikeThrough()                        | 单删除线                |
| textPosition(int textPosition)         | 行距                    |
| underline(UnderlinePatterns underline) | 下划线                  |
| textBreak()                            | 换行                    |
| beforeTab()                            | 文前Tab                 |
| afterTab()                             | 文后Tab                 |
| beforeReturn()                         | 文前回车                |
| afterReturn()                          | 文后回车                |
| upTag()                                | 设置为上标              |
| downTag()                              | 设置为下标              |

