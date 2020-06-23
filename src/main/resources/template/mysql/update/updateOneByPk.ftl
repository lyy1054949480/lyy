<#-- 修改一条数据，为空的属性也可以修改成字段值为空 -->

UPDATE ${tableName!}

<choose>
	<#-- 如果入参的 key 名称 或者 要修改的对象实例为空，则不修改任何一条数据库数据 -->
	<when test="obj==null or (key == null and key == '')">
		<set>
			<#-- 设置所有的值为原值 -->
			<#list columns as column>
				`${column.column!}`=`${column.column!}`<#if column_has_next>,</#if>
			</#list>
		</set>
		<#-- 设置修改条数为 0 -->
		LIMIT 0
	</when>
	<#-- 入参不为空的情况 -->
	<otherwise>
		<set>
			<#list columns as column>
				<choose>
					<#-- 入参要修改的实体类中所有主键属性值均为空的情况（即：无主键），则所有值均设置为原值（即：不修改） -->
					<when test=" <#list pkColumns as pkcolumn> (obj.${pkcolumn.property} == null <#if pkcolumn.javaType=='class java.lang.String'>or obj.${pkcolumn.property} ==''</#if>) <#if pkcolumn_has_next>and</#if> </#list>">
						`${column.column!}`=`${column.column!}`<#if column_has_next>,</#if>
					</when>
					<#-- 入参要修改的实体类中存在有意义值的主键属性 -->
					<otherwise>
						<choose>
							<#-- 如果当前字段（属性）不是主键，则修改其值内容 -->
						    <when test="'${column.id?c}'=='false'">
						    	`${column.column!}`=${"#\{obj."}${column.property}${"}"}<#if column_has_next>,</#if>
						    </when>
							<#-- 如果当前字段（属性）是主键，则不修改其值内容 -->
						    <otherwise>
						    	`${column.column!}`=`${column.column!}`<#if column_has_next>,</#if>
						    </otherwise>
						</choose>
					</otherwise>
				</choose>
			</#list>
		</set>
		<where>
			<#-- 主键值作为修改条件 -->
			<#list pkColumns as pkColumn>
				<if test="key == '${pkColumn.column}' and (null != obj.${pkColumn.property} <#if pkColumn.javaType=='class java.lang.String'>and obj.${pkColumn.property} !=''</#if>)">
					AND `${pkColumn.column!}`=${"#\{obj."}${pkColumn.property}${"}"}
				</if>
			</#list>
		</where>
		<#-- 入参要修改的实体类中所有主键属性值均为空的情况（即：无主键），则只修改0条数据（即：不修改） -->
		<if test=" <#list pkColumns as pkcolumn> (obj.${pkcolumn.property} == null <#if pkcolumn.javaType=='class java.lang.String'>or obj.${pkcolumn.property} ==''</#if>) <#if pkcolumn_has_next>and</#if> </#list>">
			LIMIT 0
		</if>
	</otherwise>
</choose>

