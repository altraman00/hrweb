package codeGenerate.util;

public class ColumnData
{
  public static final String OPTION_REQUIRED = "required:true";
  public static final String OPTION_NUMBER_INSEX = "precision:2,groupSeparator:','";
  private String columnName;
  private String dataType;
  private String columnComment;
  private String columnType;
  private String charmaxLength = "";
  private String nullable;
  private String scale;
  private String precision;
  private String classType = "";
  private String optionType = "";

  public String getColumnName()
  {
    return this.columnName; }

  public void setColumnName(String columnName) {
    this.columnName = columnName; }

  public String getDataType() {
    return this.dataType; }

  public void setDataType(String dataType) {
    this.dataType = dataType; }

  public String getColumnComment() {
    return this.columnComment; }

  public void setColumnComment(String columnComment) {
    this.columnComment = columnComment; }

  public String getScale() {
    return this.scale; }

  public String getPrecision() {
    return this.precision; }

  public void setScale(String scale) {
    this.scale = scale; }

  public void setPrecision(String precision) {
    this.precision = precision; }

  public String getClassType() {
    return this.classType; }

  public String getOptionType() {
    return this.optionType; }

  public String getCharmaxLength() {
    return this.charmaxLength; }

  public String getNullable() {
    return this.nullable; }

  public void setClassType(String classType) {
    this.classType = classType; }

  public void setOptionType(String optionType) {
    this.optionType = optionType; }

  public void setCharmaxLength(String charmaxLength) {
    this.charmaxLength = charmaxLength; }

  public void setNullable(String nullable) {
    this.nullable = nullable; }

  public String getColumnType() {
    return this.columnType; }

  public void setColumnType(String columnType) {
    this.columnType = columnType;
  }
}