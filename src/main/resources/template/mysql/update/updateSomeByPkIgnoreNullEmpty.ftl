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
    		<choose>
				<#-- 遍历所有主键，获取指定的主键，使之能生成 mapper.xml 中的 case `key` 结构语句，如果入参key的值是所操作数据库表的主键字段名的话 -->
		    	<#list pkColumns as pkColumn>
	    		<when test="key == '${pkColumn.column}'">
	    			<#list unPkColumns as unPkColumn>
	    			`${unPkColumn.column!}` = 
	    			<foreach collection="beans" item="bean" index="index"	separator=" " open="case `${pkColumn.column}`" close="end">
						when  
							${"#\{bean."}${pkColumn.property}${"}"} 
						then 
							<choose>
								<#-- 入参属性值不为空 -->
								<when test="bean.${unPkColumn.property} != null <#if unPkColumn.javaType=='class java.lang.String'>and bean.${unPkColumn.property} !=''</#if> ">
									${"#\{bean."}${unPkColumn.property}${"}"}
								</when>
								<#-- 入参属性值为空 -->
								<otherwise>
									`${unPkColumn.column!}`
								</otherwise>
							</choose>
					</foreach>
					<#if unPkColumn_has_next>,</#if>
	    			</#list>
	    		</when>
		    	</#list>

				<#-- 入参key的值不是所操作的数据库表的主键字段名  -->
				<otherwise>
					<#list pkColumns as pkColumn>
					`${pkColumn.column!}`=`${pkColumn.column!}`
					<#break>
			    	</#list>
				</otherwise>
    		</choose>
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
    	<choose>
			<#list pkColumns as pkColumn>
			<when test="key == '${pkColumn.column}'">
			</when>
			</#list>
			<otherwise>
			LIMIT 0
			</otherwise>
    	</choose>
	</otherwise>
</choose>