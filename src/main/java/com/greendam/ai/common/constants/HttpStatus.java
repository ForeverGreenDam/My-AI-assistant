package com.greendam.ai.common.constants;

/**
 * <h1>HttpStatus - 返回状态码</h1>
 * <p>
 * 该类定义了常用的 HTTP 状态码常量，方便在项目中统一使用，避免魔法数字的出现。
 * </p>
 *
 * <h2>功能说明：</h2>
 * <ul>
 *     <li>提供了常见的 HTTP 状态码的常量定义。</li>
 *     <li>状态码涵盖了成功、重定向、客户端错误、服务器错误等类别。</li>
 *     <li>方便在代码中直接引用，提高可读性和维护性。</li>
 * </ul>
 *
 * <h2>使用场景：</h2>
 * <p>
 * 当需要在应用程序中使用 HTTP 状态码时，可以直接引用此类中的常量。例如，在返回响应结果时设置状态码，或在处理异常时根据状态码进行相应的处理。
 * </p>
 *
 * <h2>示例：</h2>
 * <pre>{@code
 * if (responseCode == HttpStatus.SUCCESS) {
 *     // 处理成功的逻辑
 * } else if (responseCode == HttpStatus.ERROR) {
 *     // 处理错误的逻辑
 * }
 * }</pre>
 */

public interface HttpStatus {

    /**
     * <h2>SUCCESS - 操作成功</h2>
     * <p>
     * 表示请求已成功处理，对应的 HTTP 状态码是 200。
     * </p>
     */
    public static final int SUCCESS = 200;

    /**
     * <h2>CREATED - 对象创建成功</h2>
     * <p>
     * 请求已经成功，并且创建了新的资源，对应的 HTTP 状态码是 201。
     * </p>
     */
    public static final int CREATED = 201;

    /**
     * <h2>ACCEPTED - 请求已经被接受</h2>
     * <p>
     * 服务器已接受请求，但尚未处理，对应的 HTTP 状态码是 202。
     * </p>
     */
    public static final int ACCEPTED = 202;

    /**
     * <h2>NO_CONTENT - 操作已经执行成功，但是没有返回数据</h2>
     * <p>
     * 请求成功但不返回内容，对应的 HTTP 状态码是 204。
     * </p>
     */
    public static final int NO_CONTENT = 204;

    /**
     * <h2>MOVED_PERM - 资源已被移除</h2>
     * <p>
     * 资源已永久移动到新位置，对应的 HTTP 状态码是 301。
     * </p>
     */
    public static final int MOVED_PERM = 301;

    /**
     * <h2>SEE_OTHER - 重定向</h2>
     * <p>
     * 请求的资源存在另一个 URI，应使用 GET 方法定向获取，对应的 HTTP 状态码是 303。
     * </p>
     */
    public static final int SEE_OTHER = 303;

    /**
     * <h2>NOT_MODIFIED - 资源没有被修改</h2>
     * <p>
     * 未按预期修改资源，对应的 HTTP 状态码是 304。
     * </p>
     */
    public static final int NOT_MODIFIED = 304;

    /**
     * <h2>BAD_REQUEST - 参数列表错误</h2>
     * <p>
     * 请求参数有误（缺少或格式不匹配），对应的 HTTP 状态码是 400。
     * </p>
     */
    public static final int BAD_REQUEST = 400;

    /**
     * <h2>UNAUTHORIZED - 未授权</h2>
     * <p>
     * 请求需要用户的身份认证，对应的 HTTP 状态码是 401。
     * </p>
     */
    public static final int UNAUTHORIZED = 401;

    /**
     * <h2>FORBIDDEN - 访问受限，授权过期</h2>
     * <p>
     * 服务器理解请求，但拒绝执行，对应的 HTTP 状态码是 403。
     * </p>
     */
    public static final int FORBIDDEN = 403;

    /**
     * <h2>NOT_FOUND - 资源，服务未找到</h2>
     * <p>
     * 服务器无法找到请求的资源，对应的 HTTP 状态码是 404。
     * </p>
     */
    public static final int NOT_FOUND = 404;

    /**
     * <h2>BAD_METHOD - 不允许的 HTTP 方法</h2>
     * <p>
     * 请求方法被禁止，对应的 HTTP 状态码是 405。
     * </p>
     */
    public static final int BAD_METHOD = 405;

    /**
     * <h2>CONFLICT - 资源冲突，或者资源被锁</h2>
     * <p>
     * 请求冲突，例如多个更新之间的冲突，对应的 HTTP 状态码是 409。
     * </p>
     */
    public static final int CONFLICT = 409;

    /**
     * <h2>UNSUPPORTED_TYPE - 不支持的数据，媒体类型</h2>
     * <p>
     * 请求的格式不受支持，对应的 HTTP 状态码是 415。
     * </p>
     */
    public static final int UNSUPPORTED_TYPE = 415;

    /**
     * <h2>ERROR - 系统内部错误</h2>
     * <p>
     * 服务器内部错误，对应的 HTTP 状态码是 500。
     * </p>
     */
    public static final int ERROR = 500;

    /**
     * <h2>NOT_IMPLEMENTED - 接口未实现</h2>
     * <p>
     * 服务器不支持当前请求所需的某个功能，对应的 HTTP 状态码是 501。
     * </p>
     */
    public static final int NOT_IMPLEMENTED = 501;

    /**
     * <h2>WARN - 系统警告消息</h2>
     * <p>
     * 自定义的警告状态码，用于系统级别的警告信息，对应的状态码是 601。
     * </p>
     */
    public static final int WARN = 601;

}
