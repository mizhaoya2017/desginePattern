package com.mzy.shejimoshi.PrototypePatternOfSelfBeanUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * 对Model的一些通用操作
 */
public class ModelDealUtil {

    /**
     * 字段set的类型-不应当更新的字段列表
     */
    public static final int FIELDS_TYPE_CAN_NOT_EDIT = 0;

    /**
     * 字段set的类型-指定更新的字段列表
     */
    public static final int FIELDS_TYPE_SPECIFIED = 1;

    /**
     * 对比两个对象的信息，然后<b>needUpdateFields指定的</b>新的字段值赋值到原有Model之中，注意只有数据库中有的字段（排除具有注解、
     * 静态的字段）才会被更新；并且此函数不会更新继承于父类的字段
     * @param oldModel 原有Model
     * @param newModel 新的Model
     * @param needUpdateFields 需要被更新的字段列表
     * @param <T> Model的类型
     */
    public static <T> void updateModelSpecifiedFields(T oldModel, T newModel, Set<String> needUpdateFields)
            throws Exception {
        if(needUpdateFields == null || needUpdateFields.isEmpty()) {
            return;
        }

        deepUpdateModelFields(oldModel, newModel, needUpdateFields, FIELDS_TYPE_SPECIFIED, false, false);
    }

    /**
     * 对比两个对象的信息，然后新的字段值赋值到原有Model之中，注意只有数据库中有的字段才会被更新（排除具有注解、静态的字段），
     * 并且此函数不会更新继承于父类的字段
     *
     * @param oldModel 原有Model
     * @param newModel 新的Model
     * @param canNotEditFields 不能被更新的字段
     * @param <T> Model的类型
     */
    public static <T> void updateModelFields(T oldModel, T newModel, Set<String> canNotEditFields) throws Exception {
        if(canNotEditFields == null) {
            canNotEditFields = new HashSet<String>();
        }
        deepUpdateModelFields(oldModel, newModel, canNotEditFields, FIELDS_TYPE_CAN_NOT_EDIT, false, false);
    }

    /**
     * 对比两个对象的信息，然后<b>needUpdateFields指定的</b>新的字段值赋值到原有Model之中，注意只有数据库中有的字段才会被更新
     * （排除具有注解、静态的字段）
     *
     * @param oldModel 原有Model
     * @param newModel 新的Model
     * @param fieldsSet 字段名列表
     * @param fieldsSetType 字段名列表的类型：0-不应当更新的字段列表，1-指定更新的字段列表
     * @param isAllowNull 是否允许将引用类型更新为Null
     * @param deepFlag 是否进行深层次更新（即将继承于父类的字段也更新了）
     */
    public static <T> void deepUpdateModelFields(T oldModel, T newModel, Set<String> fieldsSet, int fieldsSetType,
                                                 boolean isAllowNull, boolean deepFlag) throws Exception {
        //根据是否取深层次更新，取需要更新的字段
        Field[] fields = deepFlag ? getAllClassField(oldModel.getClass()) : oldModel.getClass().getDeclaredFields();
        Object newVal;

        for (Field field : fields) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            int modifier =  field.getModifiers();

            //排除具有注解、静态的字段
            if((annotations == null || annotations.length == 0)
                    && !Modifier.isFinal(modifier) && !Modifier.isStatic(modifier)) {
                //根据字段名列表的类型，判断当前的字段是否应该进行更新
                if((FIELDS_TYPE_CAN_NOT_EDIT == fieldsSetType && !fieldsSet.contains(field.getName()))
                        || (FIELDS_TYPE_SPECIFIED == fieldsSetType && fieldsSet.contains(field.getName()))) {

                    field.setAccessible(true);
                    newVal = field.get(newModel);

                    //如果新Model的字段值不为空，可以直接更新字段；否则判断是否指定允许设置空值，允许才更新字段
                    if (newVal != null || isAllowNull) {
                        field.set(oldModel, newVal);
                    }
                }
            }
        }
    }

    /**
     * 删除列表<code>list</code>之中，名为<code>fieldName</code>的字段值出现重复的项目，
     * @param list 需要进行处理的列表
     * @param tClass 列表元素<code>T</code>的类型
     * @param fieldName 判断是否重复的的字段名
     * @param <T> 列表元素的类型
     * @return 返回去重之后的列表
     * @throws NoSuchFieldException 发生的字段名不存在的异常
     * @throws IllegalAccessException 获取字段值出现的异常
     */
    public static <T> List<T> removeListDuplicateElement(List<T> list, Class<T> tClass, String fieldName)
            throws NoSuchFieldException, IllegalAccessException {
        //参数判断
        if(list == null) {
            return null;
        } else if(tClass == null || fieldName == null || fieldName.isEmpty()) {
            throw new IllegalArgumentException("tClass, fieldName 不能为空或是null");
        }

        Set<Object> listElementFieldSet = new HashSet<Object>();
        List<T> resultList = new ArrayList<T>(list.size());

        //准备反射获取字段fieldName的值
        Field field = tClass.getDeclaredField(fieldName);

        field.setAccessible(true);

        //遍历列表，然后只保留不重复的数据
        for(T element : list) {
            if(!listElementFieldSet.contains(field.get(element))) {
                listElementFieldSet.add(field.get(element));
                resultList.add(element);
            }
        }

        return resultList;
    }

    /**
     * 获取指定类型<code>T</code>的<code>model</code>之中为null以及 类型为<code>String</code>的空白 的字段名称集合，注意，这个方法并不会深层次地获取父类的字段
     * @param model 指定类型<code>T</code>的<code>model</code>
     * @param excludeFields 需要排除的字段
     * @param <T> 指定的类型
     * @return 返回找到的为<code>null</code> 以及 类型为<code>String</code>的空白的字段名称集合
     * @throws IllegalAccessException 反射发生的异常
     */
    public static <T> Set<String> getAllNullAndBlankFields(T model, Set<String> excludeFields) throws IllegalAccessException {
        return getAllNullOrBlankFields(model, excludeFields, false, true);
    }

    /**
     * 获取指定类型<code>T</code>的<code>model</code>之中为null或者类型为<code>String</code>的空白的字段名称集合
     * @param model 指定类型<code>T</code>的<code>model</code>
     * @param excludeFields 需要排除的字段
     * @param deepFlag 是否深层次地获取父类的字段
     * @param containsBlank 查询结果是否需要包含值为空白的字段
     * @param <T> 指定的类型
     * @return 返回找到的为<code>null</code>或者类型为<code>String</code>的空白的字段名称集合
     * @throws IllegalAccessException 反射发生的异常
     */
    public static <T> Set<String> getAllNullOrBlankFields(T model, Set<String> excludeFields, boolean deepFlag,
                                                          boolean containsBlank) throws IllegalAccessException {
        //处理一下参数
        if(excludeFields == null) {
            excludeFields = new HashSet<String>();
        }

        Set<String> nullOrBlankFieldSet = new HashSet<String>();

        //遍历mainClient的字段，找到为null的，这些字段就是可以进行更新的
        Field[] modelFields = deepFlag ? getAllClassField(model.getClass()) : model.getClass().getDeclaredFields();

        if(modelFields != null) {
            for(Field modelField : modelFields) {
                //不为不能更新的字段，然后判断值是否为null
                if(!excludeFields.contains(modelField.getName())) {
                    modelField.setAccessible(true);

                    Object modelFieldValue = modelField.get(model);

                    if(modelFieldValue == null
                            || (containsBlank && String.class == modelFieldValue.getClass() && ((String) modelFieldValue).isEmpty())) {
                        nullOrBlankFieldSet.add(modelField.getName());
                    }
                }
            }
        }

        return nullOrBlankFieldSet;
    }

    /**
     * 获取Class类型为clazz的类的所有字段
     * @param clazz Class类型
     * @return 返回“不是static、final, 以及注解为@Transient”的所有类字段
     */
    public static Field[] getAllClassField(Class clazz) {
        Class<?> clazzTmp = clazz;
        List<Field> fieldList = new ArrayList<Field>();

        //遍历其父类直到java.lang.Object，排除static、final格式的字段, 以及注解为@Transient的字段
        while (Object.class != clazzTmp) {
            fieldList.addAll(Arrays.asList(clazzTmp.getDeclaredFields()));

            clazzTmp = clazzTmp.getSuperclass();
        }

        return fieldList.toArray(new Field[fieldList.size()]);
    }
}
