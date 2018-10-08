package ${packagePath};

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

<#list importClassPaths as importClassPath>
    <#if importClassPath != "">
import ${importClassPath}
    </#if>
</#list>
import com.sinoservices.minima.common.exception.TableException;
import com.sinoservices.minima.common.exception.TableOperation;
import com.sinoservices.minima.mybatis.paging.Pagination;
import com.sinoservices.minima.mybatis.paging.RowBounds;


@Service
public class ${className}BizImpl implements ${className}Biz {

    private static final String ${uppercaseClassName}_TABLE = "${prefixLowercaseClassName}";
    private final Logger logger = LoggerFactory.getLogger(${className}BizImpl.class);

    @Autowired
    private ${className}Mapper ${prefixLowercaseClassName}Mapper;

    public Pagination selectPage(${className}Condition pagingCondition) {
        Assert.notNull(pagingCondition, "${className} condition can not be null.");

        RowBounds rowBounds = pagingCondition.getRowBounds();
        List ${prefixLowercaseClassName}s = ${prefixLowercaseClassName}Mapper.select(pagingCondition, rowBounds);

        Integer count = ${prefixLowercaseClassName}Mapper.count(pagingCondition);
        Pagination page = new Pagination(count, rowBounds);
        page.setData(${prefixLowercaseClassName}s);
        return page;
    }

    public List<${className}> select(${className}Condition condition) {
	    Assert.notNull(condition, "${className} condition can not be null.");

	    return ${prefixLowercaseClassName}Mapper.select(condition);
	}

	public ${className} selectOne(${className}Condition condition) {
	    Assert.notNull(condition, "${className} condition can not be null.");

	    return ${prefixLowercaseClassName}Mapper.selectOne(condition);
	}

	public void insert(${className} ${prefixLowercaseClassName}) throws TableException {
        Assert.notNull(${prefixLowercaseClassName}, "${prefixLowercaseClassName} can not be null.");

        if (${prefixLowercaseClassName}Mapper.insert(${prefixLowercaseClassName}) <= 0) {
            throw new TableException(TableOperation.Insert,  ${uppercaseClassName}_TABLE);
        }
	}

	public void batchInsert(List<${className}> ${prefixLowercaseClassName}s) {
		Assert.notEmpty(${prefixLowercaseClassName}s, "${prefixLowercaseClassName}s can not be empty.");

		if (${prefixLowercaseClassName}Mapper.batchInsert(${prefixLowercaseClassName}s) <= 0) {
		    throw new TableException(TableOperation.BatchInsert,  ${uppercaseClassName}_TABLE);
		}
    }

    public void update(${className} ${prefixLowercaseClassName}) {
		Assert.notNull(${prefixLowercaseClassName}, "${prefixLowercaseClassName} can not be null.");

		if (${prefixLowercaseClassName}Mapper.update(${prefixLowercaseClassName}) <= 0) {
		    throw new TableException(TableOperation.Update,  ${uppercaseClassName}_TABLE);
		}
    }

    public void batchUpdate(List<${className}> ${prefixLowercaseClassName}s) {
        Assert.notEmpty(${prefixLowercaseClassName}s, "${prefixLowercaseClassName}s can not be empty.");

        if (${prefixLowercaseClassName}Mapper.batchUpdate(${prefixLowercaseClassName}s) <= 0) {
            throw new TableException(TableOperation.BatchUpdate,  ${uppercaseClassName}_TABLE);
        }
    }

    public void deleteById(Long id) {
        Assert.isTrue(id > 0, "The id must be great than 0.");

        if (${prefixLowercaseClassName}Mapper.deleteById(id) <= 0) {
            throw new TableException(TableOperation.Delete,  ${uppercaseClassName}_TABLE);
        }
    }

    public void deleteByIds(List<Long> ids) {
        Assert.notEmpty(ids, "${className} Ids can not be empty.");

        if (${prefixLowercaseClassName}Mapper.deleteByIds(ids) <= 0) {
            throw new TableException(TableOperation.BatchDelete,  ${uppercaseClassName}_TABLE);
        }
    }

    @Override
    public void logicBatchDelete(List<${className}> ${prefixLowercaseClassName}List) {
        Assert.notEmpty(${prefixLowercaseClassName}List, "${prefixLowercaseClassName}List can not be empty.");

        int deleteSuccessFlag = ${prefixLowercaseClassName}Mapper.batchDelete(${prefixLowercaseClassName}List);
        if (deleteSuccessFlag <= 0) {
            throw new TableException(TableOperation.BatchDelete,  ${uppercaseClassName}_TABLE);
        }
    }

}
