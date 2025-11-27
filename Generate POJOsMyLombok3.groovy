import com.intellij.database.model.DasTable
import com.intellij.database.model.DasTableKey
import com.intellij.database.util.Case
import com.intellij.database.util.DasUtil

/*
 * Available context bindings:
 *   SELECTION   Iterable<DasObject>
 *   PROJECT     project
 *   FILES       files helper
 */

packageName = "org.ys.common.api.entity;"    // 此处指定包路径，也就对应实体类中的package com.topex.admin.entity;
typeMapping = [    // 此处指定对应的类型映射，如下：数据库中bigint对应生成java的Long，int|tinyint生成Integer...
                   (~/(?i)bool|boolean/)            : "Boolean",
                   (~/(?i)bigint/)                      : "Long",
                   (~/(?i)int|tinyint/)                 : "Integer",
                   (~/(?i)bit/)                         : "int",
                   (~/(?i)float|double|decimal|real/): "java.math.BigDecimal",
                   (~/(?i)datetime|timestamp/)       : "java.sql.Timestamp",
                   (~/(?i)date/)                     : "java.sql.Date",
                   (~/(?i)time/)                     : "java.sql.Time",
                   (~/(?i)/)                         : "String"
]

FILES.chooseDirectoryAndSave("Choose directory", "Choose where to store generated files") { dir ->
  SELECTION.filter { it instanceof DasTable }.each { generate(it, dir) }
}

def generate(table, dir) {
  def className = javaName(table.getName(),true)
  def fields = calcFields(table)
  new PrintWriter(new OutputStreamWriter(new FileOutputStream( new File(dir, className + ".java")), "utf-8")).withPrintWriter { out -> generate(out,table, className, fields) }
}

def generate(out, table,className, fields) {   // 从这里开始，拼实体类的具体逻辑代码
  out.println "package $packageName"
  out.println ""
  out.println ""
  out.println "import lombok.Data;"     // 因为我使用了lombok插件，使用到了Data注解，所以在引包时加了这一行
  //out.println "import io.swagger.annotations.ApiModelProperty;"   // 同上，使用了swagger文档，所以引入到需要的注解
 // out.println "import javax.persistence.Id;"  // tk.mybatis插件需用时需要@id注解，所以引入，不需要就去掉
  out.println "import com.ysyy.common.annotation.objecttable.Id;";
    out.println "import com.ysyy.common.annotation.objecttable.Column;";
  out.println "import com.ysyy.common.annotation.objecttable.Table;";
  out.println ""
  out.println "@Data"
  out.println "@Table(name= \"${table.getName()}\")"
  out.println "public class $className {"
  out.println ""
  int i = 0
  fields.each() {   // 遍历字段，按下面的规则生成


    // 输出注释，这里唯一的是id特殊判断了一下，如果判断it.name == id, 则多添加一行@Id
//
////    if (it.name == "id") {
//////      if (!isNotEmpty(it.commoent)) {
////        out.println "\t/**"
////        out.println "\t * 主键id"
////        out.println "\t */"
////        out.println "\t@ApiModelProperty(value = \"主键id\", position = ${i})"
////      }
////      out.println "\t@Id"
////    }
    if (isNotEmpty(it.commoent)) {
      out.println "\t/**"
      out.println "\t * ${it.commoent}"
      out.println "\t */"
     // out.println "\t@ApiModelProperty(value = \"${it.commoent}\", position = ${i})"
    }

    // 默认表的第一个字段为主键
    if (i==0) {
      if (!isNotEmpty(it.commoent)) {
        out.println "\t/** V3版本"
        out.println "\t * 主键 "
        out.println "\t */"
      }
     //out.println "\t@Id"
      out.println "@Id(name= \"${it.name}\")"
    }
    else
    {
      out.println "@Column(name= \"${it.name}\")"
    }

    if (it.annos != "") out.println "  ${it.annos}"
    out.println "\tprivate ${it.type} ${javaName(it.name,false)};"
    out.println ""
    i++
  }
  out.println ""
  out.println "}"
}

def calcFields(table) {
  DasUtil.getColumns(table).reduce([]) { fields, col ->
    def spec = Case.LOWER.apply(col.getDataType().getSpecification())
    def typeStr = typeMapping.find { p, t -> p.matcher(spec).find() }.value

    fields += [[
                      name : javaNameMy2(col.getName(), false),
                      key:"",
                       type : typeStr,
                       commoent: col.getComment(),
                       annos: ""]]
  }
}

def isNotEmpty(content) {
  return content != null && content.toString().trim().length() > 0
}
//骆驼规则 capitalize=true 首字母大写 false=首字母小写；join就是2个单词间 拼接符
def javaName(str, capitalize) {
  def s = com.intellij.psi.codeStyle.NameUtil.splitNameIntoWords(str)
          .collect { Case.LOWER.apply(it).capitalize() }
          .join("")
          .replaceAll(/[^\p{javaJavaIdentifierPart}[_]]/, "_")
  capitalize || s.length() == 1? s : Case.LOWER.apply(s[0]) + s[1..-1]
}

def javaName2(str, capitalize) {
    def s = com.intellij.psi.codeStyle.NameUtil.splitNameIntoWords(str)
            .collect { Case.LOWER.apply(it).capitalize() }
            .join("")
            .replaceAll(/[^\p{javaJavaIdentifierPart}[_]]/, "_")
    capitalize || s.length() == 1? Case.UPPER.apply(s[0]) + (s.length() == 1?"":s[1..-1]) : Case.LOWER.apply(s[0]) + s[1..-1]
}


//骆驼规则保留 分隔符 全小写
def javaNameMy(str, capitalize) {
  def s =
          str.toLowerCase().split('_')
                  .collect { Case.LOWER.apply(it).capitalize() }
                  .join("_")
                  .replaceAll(/[^\p{javaJavaIdentifierPart}[_]]/, "_")
  capitalize || s.length() == 1? s : Case.LOWER.apply(s[0]) + s[1..-1]
}

//骆驼规则保留 分隔符 全小写；1. 先按分割符 2.把每个数组 按照 javaName 处理
def javaNameMy2(str, capitalize) {
  def s =
          str.split('_')
                  .collect { javaName2(it,true)}
                  .join("_")
                  .replaceAll(/[^\p{javaJavaIdentifierPart}[_]]/, "_")
  capitalize || s.length() == 1? s : Case.LOWER.apply(s[0]) + s[1..-1]
}


static String changeStyle(String str, boolean toCamel){
  if(!str || str.size() <= 1)
    return str

  if(toCamel){
    String r = str.toLowerCase().split('_').collect{cc -> Case.LOWER.apply(cc).capitalize()}.join('_')
    return r[0].toLowerCase() + r[1..-1]
  }else{
    str = str[0].toLowerCase() + str[1..-1]
    return str.collect{cc -> ((char)cc).isUpperCase() ? '_' + cc.toLowerCase() : cc}.join('')
  }
}



static String genSerialID() {
  return "\tprivate static final long serialVersionUID =  " + Math.abs(new Random().nextLong()) + "L;"
}
