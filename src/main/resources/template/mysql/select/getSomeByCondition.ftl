
SELECT
<#list columns as column>
  `${column.column!}`<#if column_has_next>,</#if>
</#list>
FROM
  ${tableName!}
<where>
<#list columns as column>
	<if test=" ${column.property} != null <#if column.javaType=='class java.lang.String'>and ${column.property} !=''</#if> ">
		AND `${column.column!}` = ${"#\{"}${column.property}${"}"}
	</if>
</#list>

</where>