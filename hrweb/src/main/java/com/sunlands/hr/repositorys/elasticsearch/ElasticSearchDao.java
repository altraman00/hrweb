package com.sunlands.hr.repositorys.elasticsearch;

import java.util.List;

import com.google.gson.JsonObject;

import io.searchbox.client.JestResult;
import io.searchbox.core.SearchResult.Hit;
import io.searchbox.core.SuggestResult;

/**
 * ES操作 抽象方法 基本包含所有基本操作
* @ClassName: ElasticSearchDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author xiekun
* @date 2017年11月2日 上午11:47:55 
*
 */
public interface ElasticSearchDao {

	/**
	 * 删除索引
	 * @param type ：当前删除document名称
	 * @return
	 */
	public JestResult deleteIndex(String type) ;
	
	//清除缓存
	public JestResult clearCache() ;
	
	/**
	 * 关闭索引
	 * @param type ：文档表示的对象类别
	 * @return
	 */
	public JestResult closeIndex(String type) ;
	
	//优化索引
	public JestResult optimizeIndex() ;
	
	//刷新索引
	public JestResult flushIndex();
	
	//判断索引是否存在
	public JestResult indicesExists();
	
	//查看节点信息
	public JestResult nodesInfo();
	
	//查看集群健康信息
	public JestResult health();
	
	//节点状态
	public JestResult nodesStats();
	
	/**
	 * 更新Document
	 * @param index ：文档在哪存放
	 * @param type ： 文档表示的对象类别
	 * @param id ：文档唯一标识
	 */
	public JestResult updateDocument(String script , String index,String type,String id);
	
	/**
	 * 删除Document
	 * @param index ：文档在哪存放
	 * @param type ： 文档表示的对象类别
	 * @param id ：文档唯一标识
	 * @return
	 */
	public JestResult deleteDocument(String index,String type,String id) ;
	
	/**
	 * 获取Document
	 * @param o ：返回对象
	 * @param index ：文档在哪存放
	 * @param type ： 文档表示的对象类别
	 * @param id ：文档唯一标识
	 * @return
	 */
	public <T> JestResult getDocument(T o , String index , String type , String id) ;
	
	//Suggestion
	public List<SuggestResult.Suggestion> suggest() ;
	
	/**
	 * 查询全部
	 * @param index ：文档在哪存放
	 * @return
	 */
	public <T> List<Hit<T,Void>> searchAll(String index , T o);
	
	/**
	 * 搜索
	 * @param keyWord ：搜索关键字
	 * @return 
	 */
	public <T> List<Hit<T,Void>> createSearch(String keyWord , String type , T o , String... fields) ;
	
	//bulkIndex操作
	public <T> void bulkIndex(String index , String type , T o) ;
	
	/**
	 * 创建索引
	 * @param o ：返回对象
	 * @param index ：文档在哪存放
	 * @param type ： 文档表示的对象类别
	 * @return
	 */
	public <T> JestResult createIndex(T o , String index , String type);
	
	/**
	 * 搜索事件流图表数据
	 * @param param
	 * @return
	 */
	public JsonObject searchEvent(String param); 
	
	/**
	 * es 深度分页 第一次查值
	 * @param param
	 * @return
	 */
	public JsonObject searchInitEventHistogram(String param); 
	
	/**
	 * es 深度分页，通过scrollId查询
	 * 
	 * scrollId有效时间为1分钟
	 * 
	 * @param param
	 * @return
	 */
	public JsonObject searchEventHistogramByScroll(String scrollId); 
	
}
