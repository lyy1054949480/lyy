<#-- 修改若干条数据，忽略为空的属性 -->

UPDATE ${tableName!}

<choose>
	<#-- 如果入参的 key 名称 或者 要修改的对象实例为空，则不修改任何一条数据库数据 -->
	<when test="(beans==null or beans.length &lt; 1) or (key == null and key == '')">
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
				<#-- 如果当前字段（属性）不是主键，且值不为空，则修改其值内容 -->
				    <when test="'${column.id?c}'=='false'">
				    	`${column.column!}` =
				    	<#-- 遍历所有主键，获取指定的主键，使之能生成 mapper.xml 中的 case `key` 结构语句 -->
				    	<#list pkColumns as pkColumn>
					    	<choose>
								<when test="key == '${pkColumn.column}'">
									<foreach collection="beans" item="bean" index="index"	separator=" " open="case `${pkColumn.column}`" close="end">
										when  ${"#\{bean."}${pkColumn.property}${"}"} then 
													<choose>
														<#-- 入参属性值不为空 -->
														<when test="bean.${column.property} != null <#if column.javaType=='class java.lang.String'>and bean.${column.property} !=''</#if> ">
															${"#\{bean."}${column.property}${"}"}
														</when>
														<#-- 入参属性值为空 -->
														<otherwise>
															`${column.column!}`
														</otherwise>
													</choose>
									</foreach>
								</when>
								<otherwise>
									<#-- 遍历了所有主键后，仍无指定主键，则不对数据库该字段进行修改 -->
									<#if !pkColumn_has_next>
										`${column.column!}`
									</#if>
								</otherwise>
					    	</choose>
						</#list>
						<#if column_has_next>,</#if>
				    </when>
				</choose>
			</#list>
		</set>
		<where>
			<#-- 主键值作为修改条件 -->
			<#list pkColumns as pkColumn>
				<if test="key == '${pkColumn.column}'">
					`${pkColumn.column!}` IN
					(
						<foreach collection="beans" item="bean" index="index"	separator="," open="" close="">
							${"#\{bean."}${pkColumn.property}${"}"}
						</foreach>
					)
				</if>
			</#list>
		</where>
		
		<#-- 如果传入的“key”值不是真实存在的主键字段名，则不修改数据 -->
		<#list pkColumns as pkColumn>
	    	<choose>
				<when test="key == '${pkColumn.column}'">
				</when>
				<otherwise>
					<#if !pkColumn_has_next>
						LIMIT 0
					</#if>
				</otherwise>
	    	</choose>
		</#list>
	</otherwise>
</choose>

