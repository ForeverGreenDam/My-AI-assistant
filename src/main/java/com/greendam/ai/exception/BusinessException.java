package com.greendam.ai.exception;

import cn.hutool.core.text.StrFormatter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;

/**
 * <h1>IntegratedException - 自定义业务异常类（支持占位符 {} ）</h1>
 * <p>
 * 该类继承自 {@link RuntimeException}，用于处理业务过程中的各种异常情况。
 * 通过此异常类，可以携带特定的错误代码和消息，以便更准确地描述错误，并支持携带额外的数据用于调试或其他用途。
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public final class BusinessException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * <h2>code - 错误代码</h2>
     * <p>
     * 用于标识具体的错误类型，以便进行分类处理或国际化错误消息展示。
     * </p>
     */
    private Integer code;

    /**
     * <h2>message - 错误消息</h2>
     * <p>
     * 描述错误的具体信息，通常用于向用户展示或日志记录。
     * </p>
     */
    private String message;

    /**
     * <h2>detailMessage - 错误明细</h2>
     * <p>
     * 内部调试错误信息，提供更详细的错误描述，有助于开发人员快速定位问题。
     * </p>
     */
    private String detailMessage;

    /**
     * <h3>IntegratedException - 构造函数</h3>
     * <p>
     * 使用错误消息创建业务异常对象。
     * </p>
     *
     * @param message 错误消息
     */
    public BusinessException(String message) {
        this.message = message;
    }

    /**
     * <h3>IntegratedException - 构造函数</h3>
     * <p>
     * 使用错误消息和错误码创建业务异常对象。
     * </p>
     *
     * @param message 错误消息
     * @param code    错误码
     */
    public BusinessException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    /**
     * <h3>IntegratedException - 构造函数（支持占位符）</h3>
     * <p>
     * 使用包含占位符 {} 的错误消息模板和参数创建业务异常对象。
     * 使用 {@link StrFormatter} 格式化消息，将参数依次替换消息模板中的占位符。
     * </p>
     *
     * <h4>示例：</h4>
     * <pre>
     *     throw new IntegratedException("用户 {} 的状态无效：{}", userId, status);
     * </pre>
     *
     * @param message 包含占位符 {} 的错误消息模板
     * @param args    用于替换占位符的参数数组
     */
    public BusinessException(String message, Object... args) {
        // 使用Hutool的StrFormatter格式化消息，替换占位符
        this.message = StrFormatter.format(message, args);
    }

    /**
     * <h3>getMessage - 获取错误消息</h3>
     * <p>
     * 重写父类方法，返回业务异常的错误消息。
     * </p>
     *
     * @return 错误消息
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * <h3>setMessage - 设置错误消息</h3>
     * <p>
     * 设置业务异常的错误消息，支持链式调用。
     * </p>
     *
     * @param message 错误消息
     * @return 当前异常对象，支持链式调用
     */
    public BusinessException setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * <h3>setDetailMessage - 设置详细错误消息</h3>
     * <p>
     * 设置业务异常的详细错误信息，用于内部调试和问题定位，支持链式调用。
     * </p>
     *
     * @param detailMessage 详细错误消息
     * @return 当前异常对象，支持链式调用
     */
    public BusinessException setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
        return this;
    }
}
