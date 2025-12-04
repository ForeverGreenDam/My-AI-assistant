package com.greendam.ai.common;

import com.greendam.ai.common.constants.HttpStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * <h1>Result - 统一响应结果封装类</h1>
 *
 * <h2>主要功能</h2>
 * <p>
 * 统一封装所有API接口的响应结果,提供一致的响应格式。
 * 支持成功、失败、警告等多种响应状态,并支持泛型数据返回。
 * </p>
 *
 * <h2>响应结构</h2>
 * <ul>
 *     <li><strong>code</strong>:响应状态码(200成功,500失败,601警告)</li>
 *     <li><strong>msg</strong>:响应消息,支持国际化</li>
 *     <li><strong>data</strong>:响应数据,泛型类型</li>
 * </ul>
 *
 * <h2>核心方法</h2>
 * <ul>
 *     <li><strong>成功响应</strong>:ok()系列方法</li>
 *     <li><strong>失败响应</strong>:fail()系列方法</li>
 *     <li><strong>警告响应</strong>:warn()系列方法</li>
 *     <li><strong>状态判断</strong>:isSuccess(), isError()</li>
 * </ul>
 *
 * <h2>技术特点</h2>
 * <ul>
 *     <li><strong>泛型支持</strong>:使用泛型T灵活承载各种类型数据</li>
 *     <li><strong>国际化消息</strong>:集成MessageUtils自动国际化</li>
 *     <li><strong>链式构建</strong>:提供静态工厂方法简化创建</li>
 *     <li><strong>序列化安全</strong>:实现Serializable接口</li>
 * </ul>
 *
 * <h2>使用示例</h2>
 * <pre>{@code
 * // 成功响应(无数据)
 * return Result.ok();
 *
 * // 成功响应(带数据)
 * return Result.ok(userVo);
 *
 * // 失败响应
 * return Result.fail("操作失败");
 *
 * // 自定义状态码
 * return Result.fail(HttpStatus.BAD_REQUEST, "参数错误");
 *
 * // 警告响应
 * return Result.warn("数据即将过期");
 * }</pre>
 *
 */
@Data
@NoArgsConstructor
public class Result<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * <h4>成功状态码</h4>
     * <p>操作成功的HTTP状态码,值为200</p>
     */
    public static final int SUCCESS = HttpStatus.SUCCESS;

    /**
     * <h4>失败状态码</h4>
     * <p>操作失败的HTTP状态码,值为500</p>
     */
    public static final int FAIL = HttpStatus.ERROR;

    /**
     * <h4>响应状态码</h4>
     * <p>标识请求处理结果的状态码</p>
     */
    private Integer code;

    /**
     * <h4>响应消息</h4>
     * <p>描述请求处理结果的文字说明,支持国际化</p>
     */
    private String msg;

    /**
     * <h4>响应数据</h4>
     * <p>请求成功时返回的业务数据</p>
     */
    private T data;

    /**
     * <h3>构建成功响应（无数据）</h3>
     * <p>返回成功状态,消息为"操作成功",无业务数据</p>
     *
     * @param <T> 响应数据类型
     * @return 成功响应对象
     */
    public static <T> Result<T> ok() {
        return restResult(null, SUCCESS, "operation.successful");
    }

    /**
     * <h3>构建成功响应（带数据）</h3>
     * <p>返回成功状态,消息为"操作成功",携带业务数据</p>
     *
     * @param data 响应数据
     * @param <T>  响应数据类型
     * @return 成功响应对象
     */
    public static <T> Result<T> ok(T data) {
        return restResult(data, SUCCESS, "operation.successful");
    }

    /**
     * <h3>构建成功响应（自定义消息）</h3>
     * <p>返回成功状态,使用自定义消息,无业务数据</p>
     *
     * @param msg 自定义响应消息（支持国际化键）
     * @param <T> 响应数据类型
     * @return 成功响应对象
     */
    public static <T> Result<T> ok(String msg) {
        return restResult(null, SUCCESS, msg);
    }

    /**
     * <h3>构建成功响应（自定义消息+数据）</h3>
     * <p>返回成功状态,使用自定义消息,携带业务数据</p>
     *
     * @param msg  自定义响应消息（支持国际化键）
     * @param data 响应数据
     * @param <T>  响应数据类型
     * @return 成功响应对象
     */
    public static <T> Result<T> ok(String msg, T data) {
        return restResult(data, SUCCESS, msg);
    }

    /**
     * <h3>构建失败响应（无数据）</h3>
     * <p>返回失败状态,消息为"操作失败",无业务数据</p>
     *
     * @param <T> 响应数据类型
     * @return 失败响应对象
     */
    public static <T> Result<T> fail() {
        return restResult(null, FAIL, "operation.failed");
    }

    /**
     * <h3>构建失败响应（自定义消息）</h3>
     * <p>返回失败状态,使用自定义消息,无业务数据</p>
     *
     * @param msg 自定义失败消息（支持国际化键）
     * @param <T> 响应数据类型
     * @return 失败响应对象
     */
    public static <T> Result<T> fail(String msg) {
        return restResult(null, FAIL, msg);
    }

    /**
     * <h3>构建失败响应（带数据）</h3>
     * <p>返回失败状态,消息为"操作失败",携带额外数据</p>
     *
     * @param data 响应数据（通常为错误详情）
     * @param <T>  响应数据类型
     * @return 失败响应对象
     */
    public static <T> Result<T> fail(T data) {
        return restResult(data, FAIL, "operation.failed");
    }

    /**
     * <h3>构建失败响应（自定义消息+数据）</h3>
     * <p>返回失败状态,使用自定义消息,携带额外数据</p>
     *
     * @param msg  自定义失败消息（支持国际化键）
     * @param data 响应数据（通常为错误详情）
     * @param <T>  响应数据类型
     * @return 失败响应对象
     */
    public static <T> Result<T> fail(String msg, T data) {
        return restResult(data, FAIL, msg);
    }

    /**
     * <h3>构建失败响应（自定义状态码）</h3>
     * <p>返回失败状态,使用自定义状态码和消息</p>
     *
     * @param code 自定义状态码
     * @param msg  自定义失败消息（支持国际化键）
     * @param <T>  响应数据类型
     * @return 失败响应对象
     */
    public static <T> Result<T> fail(int code, String msg) {
        return restResult(null, code, msg);
    }

    /**
     * <h3>构建失败响应（完整参数）</h3>
     * <p>返回失败状态,支持自定义状态码、消息和数据</p>
     *
     * @param code 自定义状态码
     * @param msg  自定义失败消息（支持国际化键）
     * @param data 响应数据
     * @param <T>  响应数据类型
     * @return 失败响应对象
     */
    public static <T> Result<T> fail(int code, String msg, T data) {
        return restResult(data, code, msg);
    }

    /**
     * <h3>构建警告响应（无数据）</h3>
     * <p>返回警告状态,用于提醒用户但不影响业务流程的场景</p>
     *
     * @param msg 警告消息（支持国际化键）
     * @param <T> 响应数据类型
     * @return 警告响应对象
     */
    public static <T> Result<T> warn(String msg) {
        return restResult(null, HttpStatus.WARN, msg);
    }

    /**
     * <h3>构建警告响应（带数据）</h3>
     * <p>返回警告状态,携带业务数据</p>
     *
     * @param msg  警告消息（支持国际化键）
     * @param data 响应数据
     * @param <T>  响应数据类型
     * @return 警告响应对象
     */
    public static <T> Result<T> warn(String msg, T data) {
        return restResult(data, HttpStatus.WARN, msg);
    }


    /**
     * <h3>构建响应结果</h3>
     * <p>内部工具方法,统一构建响应对象</p>
     *
     * @param data 响应数据
     * @param code 状态码
     * @param msg  消息键或消息内容
     * @param <T>  响应数据类型
     * @return 构建好的响应对象
     */
    private static <T> Result<T> restResult(T data, int code, String msg) {
        // 创建新的响应对象
        Result<T> r = new Result<>();
        // 设置状态码
        r.setCode(code);
        // 设置响应数据
        r.setData(data);
        // 通过MessageUtils获取国际化消息并设置
        r.setMsg(msg);
        return r;
    }

    /**
     * <h3>判断响应是否失败</h3>
     * <p>检查响应对象的状态码是否为失败状态</p>
     *
     * @param ret 响应对象
     * @param <T> 响应数据类型
     * @return true：失败 false：成功
     */
    public static <T> Boolean isError(Result<T> ret) {
        return !isSuccess(ret);
    }

    /**
     * <h3>判断响应是否成功</h3>
     * <p>检查响应对象的状态码是否为成功状态</p>
     *
     * @param ret 响应对象
     * @param <T> 响应数据类型
     * @return true：成功 false：失败
     */
    public static <T> Boolean isSuccess(Result<T> ret) {
        return Result.SUCCESS == ret.getCode();
    }
}
