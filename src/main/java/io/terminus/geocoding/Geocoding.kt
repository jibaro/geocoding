package io.terminus.geocoding;

import io.terminus.geocoding.core.Context
import io.terminus.geocoding.model.Address
import io.terminus.geocoding.similarity.Document
import io.terminus.geocoding.similarity.MatchedResult

/**
 * Desc: 提供服务的主类
 * Mail: chk@terminus.io
 * Created by IceMimosa
 * Date: 2017/1/12
 */
object Geocoding {

    /**
     * 地址的标准化, 将不规范的地址清洗成标准的地址格式
     */
    fun normalizing(address: String): Address? {
        return Address.build(Context.getInterpreter().interpret(address))
    }

    /**
     * 将地址进行切分
     */
    fun analyze(address: String): Document? {
        val addr = normalizing(address) ?: return null
        return Context.getComputer().analyze(addr)
    }
    fun analyze(address: Address?): Document? {
        address ?: return null
        return Context.getComputer().analyze(address)
    }

    /**
     * 地址的相似度计算
     */
    fun similarity(addr1: String, addr2: String): Double {
        val compute = Context.getComputer().compute(normalizing(addr1), normalizing(addr2)) ?: return 0.0
        return compute.similarity
    }
    fun similarity(addr1: Address?, addr2: Address?): Double {
        val compute = Context.getComputer().compute(addr1, addr2) ?: return 0.0
        return compute.similarity
    }

    /**
     * 地址相似度计算, 包含匹配的所有结果
     */
    fun similarityWithResult(addr1: String, addr2: String): MatchedResult? {
        return Context.getComputer().compute(normalizing(addr1), normalizing(addr2))
    }
    fun similarityWithResult(addr1: Address?, addr2: Address?): MatchedResult? {
        return Context.getComputer().compute(addr1, addr2)
    }

}
