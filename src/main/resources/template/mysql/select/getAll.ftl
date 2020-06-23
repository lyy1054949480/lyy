
SELECT
<#list columns as column>
  `${column.column!}`<#if column_has_next>,</#if>
</#list>
FROM
  ${tableName!}