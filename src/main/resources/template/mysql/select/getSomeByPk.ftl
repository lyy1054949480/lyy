

SELECT
<#list columns as column>
  `${column.column!}`<#if column_has_next>,</#if>
</#list>
FROM
  ${tableName!}
<where>
<#list pkColumns as pkColumn>
	<if test="key == '${pkColumn.column}' and (null != ids and ids.length >0)">
		`${pkColumn.column}` IN
		(
		<foreach collection="ids" item="id" index="index" open="" separator="," close="">
			<if test="id != null "> ${"#\{id}"} </if>
		</foreach>
		)
	</if>
</#list>

</where>