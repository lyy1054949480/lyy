
DELETE
FROM
  ${tableName!}
<where>
<#list columns as column>
	<if test=" ${column.property} != null <#if column.javaType=='class java.lang.String'>and ${column.property} !=''</#if> ">
		AND `${column.column!}` = ${"#\{"}${column.property}${"}"}
	</if>
</#list>
</where>
<if test=" <#list columns as column> (${column.property} == null <#if column.javaType=='class java.lang.String'>or ${column.property} ==''</#if>) <#if column_has_next>and</#if> </#list>">
LIMIT 0
</if>