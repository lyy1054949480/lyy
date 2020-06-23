<#-- 批量逻辑删除（修改制定主键名称，指定主键值集合的数据的指定字段名的指定值） -->

UPDATE ${tableName!}
<choose>
	<#-- 入参有为空的情况 -->
	<when test="(stateKey==null or stateKey=='') or (stateValue==null) or (key == null or key == '') or (ids == null or ids.length &lt; 1)">
		<set>
			<#-- 设置所有的值为原值 -->
			<#list columns as column>
				`${column.column!}`=`${column.column!}`<#if column_has_next>,</#if>
			</#list>
		</set>
		<#-- 设置修改条数为 0 -->
		LIMIT 0
	</when>
	<#-- 入参均不为空的情况 -->
	<otherwise>
		<set>
			<#list columns as column>
				<if test="stateKey == '${column.column!}'">
					`${column.column!}`= ${"#\{stateValue}"}
				</if>
			</#list>
		</set>
		<where>
			<#-- 主键值作为修改条件 -->
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
	</otherwise>
</choose>
