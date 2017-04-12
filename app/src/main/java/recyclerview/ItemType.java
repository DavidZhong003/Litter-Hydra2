package recyclerview;

/**
 * Created by Administrator on 2017/4/5.
 * 条目类型适配器
 */
public interface ItemType {

    /**
     * 绑定item的类型,可用layoutId
     * @return
     */
    int bindItemType();

    <T extends Object> T bindItemData();

}
