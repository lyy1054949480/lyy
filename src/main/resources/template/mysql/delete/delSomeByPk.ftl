

DELETE
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
<if test=" key == null or key == '' or ids == null or (ids != null and ids.length &lt; 1) ">
LIMIT 0
</if>