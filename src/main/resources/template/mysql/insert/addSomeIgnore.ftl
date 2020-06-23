
INSERT IGNORE INTO ${tableName!}
(
<#list columns as column>
	`${column.column}`<#if column_has_next>,</#if>
</#list>
)
VALUES 
<foreach collection="ts" item="bean"  index="index" open="" separator="," close="">
(
<#list columns as column>
	${"#\{bean."}${column.property}${"}"}<#if column_has_next>,</#if>
</#list>
)
</foreach>