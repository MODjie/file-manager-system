<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${namespace}">
	<resultMap type="${domainPath}" id="${prefixLowercaseClassName}ResultMap">
        <#list colunmModels as column>
        <result property="${column.propertyName}" column="${column.columName}"/>
        </#list>
	</resultMap>
	<sql id="select">
		<![CDATA[
		SELECT
	   <#list colunmModels as column>
		   <#if column_has_next>
			   ${column.columName},
		   <#else >
			   ${column.columName}
		   </#if>
	   </#list>
		]]>
	</sql>

	<sql id="count">
		<![CDATA[
		SELECT COUNT(1)
		]]>
	</sql>

	<sql id="findByExample">
		WHERE 1=1 AND rec_status=0
		<!-- 上面状态强制要求为0，此处允许再过滤，即要求只查询未删除数据 -->
		<#list colunmModels as column>
        <#if column.propertyName!="recStatus">
        <if test="${column.propertyName}!=null and ${column.propertyName}!=''"> AND ${column.columName} = ${r'#{'}${column.propertyName}}</if>
        </#if>
        </#list>
	</sql>

	<select id="count" resultType="int" parameterType="${conditionPath}">
		<include refid="count"/>
		FROM  ${tableName}
		<include refid="findByExample"/>
	</select>

	<select id="select" resultMap="${prefixLowercaseClassName}ResultMap" parameterType="${conditionPath}">
		<include refid="select"/>
		FROM  ${tableName}
		<include refid="findByExample"/>
		ORDER BY create_time DESC
	</select>

	<!--返回单条记录-->
	<select id="selectOne" resultMap="${prefixLowercaseClassName}ResultMap" parameterType="${conditionPath}">
		<include refid="select"/>
		FROM  ${tableName}
		<include refid="findByExample"/>
		limit 1
	</select>

	<insert id="insert">
		INSERT INTO ${tableName} (
         <#list colunmModels as column>
			 <#if column_has_next>
				 ${column.columName},
			 <#else >
				 ${column.columName}
			 </#if>
         </#list>
		) VALUES (
         <#list colunmModels as column>
			 <#if column_has_next>
				 ${r'#{'}${column.propertyName}},
			 <#else >
				 ${r'#{'}${column.propertyName}}
			 </#if>
         </#list>
		)
	</insert>

	<insert id="batchInsert" parameterType="java.util.List">
		INSERT INTO ${tableName} (
		<#list colunmModels as column>
			<#if column_has_next>
				${column.columName},
			<#else >
				${column.columName}
			</#if>
        </#list>
		) VALUES
		<foreach collection="list" item="item" index="index" separator=",">
			(
        <#list colunmModels as column>
			<#if column_has_next>
				${r'#{'}item.${column.propertyName}},
			<#else >
				${r'#{'}item.${column.propertyName}}
			</#if>
        </#list>
			)
		</foreach>
	</insert>
	<update id="update">
		UPDATE ${tableName} SET
        <#list colunmModels as column>
            <#if column.propertyName!="recVer" && column.propertyName!=colunmModels[0].propertyName && column.propertyName!="modifyTime">
            <if test="${column.propertyName}!=null and ${column.propertyName}!=''">${column.columName} =  ${r'#{'}${column.propertyName}},</if>
            </#if>
        </#list>
		rec_ver = rec_ver + 1,
		modify_time = SYSDATE()
		WHERE ${colunmModels[0].columName} = ${r'#{'}${colunmModels[0].propertyName}} AND rec_ver = ${r'#{recVer}'} AND tenant_id = ${r'#{tenantId}'}
	</update>
	<update id="batchUpdate" parameterType="java.util.List">
		<foreach collection="list" item="item" index="index" separator=";">
			UPDATE ${tableName} SET
             <#list colunmModels as column>
             <#if column.propertyName!="recVer" && column.propertyName!=colunmModels[0].propertyName && column.propertyName!="modifyTime">
             <if test="item.${column.propertyName}!=null and item.${column.propertyName}!=''">${column.columName} =  ${r'#{item.'}${column.propertyName}},</if>
             </#if>
             </#list>
			rec_ver = rec_ver + 1,
			modify_time = SYSDATE()
			WHERE ${colunmModels[0].columName} =  ${r'#{'}item.${colunmModels[0].propertyName}} AND rec_ver = ${r' #{item.recVer}'} AND tenant_id = ${r'#{item.tenantId}'}
		</foreach>
	</update>

	<!-- 删除 -->
	<delete id="deleteById" parameterType="java.lang.Long">
		DELETE FROM  ${tableName}
		WHERE ${colunmModels[0].columName} = ${r'#{'}${colunmModels[0].propertyName}}
	</delete>

	<!-- 删除 -->
	<delete id="deleteByIds" parameterType="java.util.List">
		DELETE FROM  ${tableName}
		WHERE
		<choose>
			<when test="list!=null and list.size>0">
				id IN
				<foreach collection="list" index="index" item="id" open="(" separator="," close=")">
                ${r'#{id}'}
				</foreach>
			</when>
			<otherwise>1!=1</otherwise>
		</choose>
	</delete>

	<!-- 删除(逻辑删除) -->
	<update id="delete" parameterType="${domainPath}">
		UPDATE ${tableName}
		SET rec_status = 1,
		rec_ver = rec_ver + 1,
		modify_time = SYSDATE()
		WHERE ${colunmModels[0].columName} = ${r'#{'}${colunmModels[0].propertyName}} AND rec_ver = ${r' #{recVer}'} AND tenant_id = ${r'#{tenantId}'}
	</update>

	<!-- 删除(逻辑删除)-->
	<update id="batchDelete" parameterType="java.util.List">
		UPDATE ${tableName}
		SET rec_status = 1,
		rec_ver = rec_ver + 1,
		modify_time = SYSDATE()
		WHERE
		<choose>
			<when test="list!=null and list.size()>0">
				(${colunmModels[0].columName},rec_ver,tenant_id) IN
				<foreach collection="list" item="item" index="index" open="(" separator="," close=")">
					(${r'#{item.'}${colunmModels[0].columName}},${r'#{item.recVer}'},${r'#{item.tenantId}'})
				</foreach>
			</when>
			<otherwise>1!=1</otherwise>
		</choose>
	</update>
</mapper>
