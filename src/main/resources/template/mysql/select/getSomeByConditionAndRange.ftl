<#-- 根据条件，且可以指定一个字段使用范围条件获取对应数据集合 -->

SELECT
<#list columns as column>
  `${column.column!}`<#if column_has_next>,</#if>
</#list>
FROM
  ${tableName!}
<where>
<#list columns as column>
		<choose>
			<#-- 如果当前条件属性不为空-->
			<when test="condition!=null and condition.${column.property} != null <#if column.javaType=='class java.lang.String'>and condition.${column.property} !=''</#if> ">
				<choose>
					<#-- 如果当前列名是指定查询范围的列名  -->
					<when test=" column == '${column.column}' ">
						<if test = "min != null">
							AND `${column.column!}` <![CDATA[>=]]> ${"#\{min}"}
						</if>
						<if test = " max != null">
							AND `${column.column!}` <![CDATA[<=]]> ${"#\{max}"}
						</if>
					</when>
					<#-- 如果当前列名 不 是指定查询范围的列名  -->
					<otherwise>
						AND `${column.column!}` = ${"#\{condition."}${column.property}${"}"}
					</otherwise>
				</choose>
			</when>
			<#--如果当前条件属性为空，可能是指定查询范围的字段名称-->
			<otherwise>
				<if test=" column == '${column.column}' ">
					<if test = "min != null">
						AND `${column.column!}` <![CDATA[>=]]> ${"#\{min}"}
					</if>
					<if test = " max != null">
						AND `${column.column!}` <![CDATA[<=]]> ${"#\{max}"}
					</if>
				</if>
			</otherwise>
		</choose>
</#list>

</where>