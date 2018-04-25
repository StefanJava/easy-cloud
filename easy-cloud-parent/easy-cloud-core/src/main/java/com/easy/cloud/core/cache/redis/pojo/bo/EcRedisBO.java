package com.easy.cloud.core.cache.redis.pojo.bo;

import org.aspectj.lang.ProceedingJoinPoint;

import com.easy.cloud.core.basic.factory.EcBeanFactory;
import com.easy.cloud.core.basic.pojo.bo.EcBaseAspectBO;
import com.easy.cloud.core.cache.redis.annotation.EcRedisAnnotation;
import com.easy.cloud.core.cache.redis.pojo.dto.EcRedisDTO;
import com.easy.cloud.core.cache.redis.proxy.EcRedisProxy;

/**
 * redis业务逻辑类
 * 
 * @author daiqi
 * @date 2018年4月20日 下午10:48:05
 */
public class EcRedisBO extends EcBaseAspectBO {
	/** redis数据传输对象 */
	private EcRedisDTO redisDTO;
	/** redis注解对象 */
	private EcRedisAnnotation redisAnnotation;
	
	public EcRedisBO() {
		
	}
	public EcRedisBO(EcRedisDTO redisDTO) {
		this.redisDTO = redisDTO;
	}

	public EcRedisBO buildRedisData(ProceedingJoinPoint pjp) {
		super.buildBaseAspectData(pjp);
		this.buildRedisAnnotation();
		return this;
	}
	
	public Object handle() throws Throwable {
		Class<? extends EcRedisProxy> handleClass = redisAnnotation.proxyClass();
		EcRedisProxy redisProxy = EcBeanFactory.newInstance(handleClass);
		if (redisProxy == null) {
			return super.process();
		}
		return redisProxy.handle(this);
	}
	
	private EcRedisBO buildRedisAnnotation() {
		this.redisAnnotation = super.targetMethod.getAnnotation(EcRedisAnnotation.class);
		return this;
	}

	public EcRedisAnnotation getRedisAnnotation() {
		return redisAnnotation;
	}

	public EcRedisDTO geRedisDTO() {
		return this.redisDTO;
	}
}
