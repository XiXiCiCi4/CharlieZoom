package org.example1.charlie_zoom.client;

/**
 * 缩放状态持有者。
 *
 * - {@code lastFov}：原版未缩放的实际视野（缩放期间冻结），由 GameRenderer#getFov
 *   在非缩放时记录，已包含速度/药水效果加成。
 * - {@code targetFov}：缩放目标视野，默认 30°，放大时滚动滚轮可调（每格
 *   {@link #SCROLL_STEP} 度，范围 {@link #MIN_ZOOM_FOV}~{@link #MAX_ZOOM_FOV}，
 *   突破原版 30~110 限制）。
 * - {@code currentFov}：平滑动画当前值，指数缓动向目标收敛（先快后慢，
 *   约 0.1~0.2s 完成）。
 */
public final class ZoomState {

    public static final ZoomState INSTANCE = new ZoomState();

    /** 缩放目标视野默认值（度）。 */
    public static final double DEFAULT_ZOOM_FOV = 30.0;
    /** 缩放视野下限（度）：比原版 30 更低，接近望远镜级别。 */
    public static final double MIN_ZOOM_FOV = 10.0;
    /** 缩放视野上限（度）：比原版 110 更高。 */
    public static final double MAX_ZOOM_FOV = 170.0;
    /** 滚轮每格调整的视野（度）。 */
    public static final double SCROLL_STEP = 10.0;
    /** 动画收敛阈值（度）。 */
    private static final double EPSILON = 0.01;
    /** 指数缓动系数：每次 getFov 调用更新一步，整体约 0.1~0.2s 完成过渡。 */
    private static final double DAMPING = 0.2;

    private boolean zooming;
    private double lastFov = 70.0;
    private double targetFov = DEFAULT_ZOOM_FOV;
    private double currentFov = 70.0;

    private ZoomState() {
    }

    public boolean isZooming() {
        return this.zooming;
    }

    /** 切换缩放状态；从非缩放进入缩放时，从当前原版视野开始缓动。 */
    public void setZooming(boolean zooming) {
        if (zooming && !this.zooming) {
            this.currentFov = this.lastFov;
        }
        this.zooming = zooming;
    }

    /** 记录原版未缩放视野；缩放期间冻结不更新。 */
    public void setLastFov(double fov) {
        if (!this.zooming) {
            this.lastFov = fov;
        }
    }

    public double getLastFov() {
        return this.lastFov;
    }

    public double getTargetFov() {
        return this.targetFov;
    }

    /** 当前动画视野（缩放时渲染使用）。 */
    public double getCurrentFov() {
        return this.currentFov;
    }

    /** 推进平滑动画一步（指数缓动，先快后慢）。 */
    public void updateAnimation() {
        this.currentFov += (this.targetFov - this.currentFov) * DAMPING;
        if (Math.abs(this.targetFov - this.currentFov) < EPSILON) {
            this.currentFov = this.targetFov;
        }
    }

    /** 滚轮调整缩放目标视野：上滚放大（视野减小），下滚缩小，每格 SCROLL_STEP 度。 */
    public void adjustFovByScroll(double scrollVertical) {
        if (scrollVertical == 0) {
            return;
        }
        double delta = -Math.signum(scrollVertical) * SCROLL_STEP;
        this.targetFov = Math.max(MIN_ZOOM_FOV, Math.min(MAX_ZOOM_FOV, this.targetFov + delta));
    }

    /** 缩放时的鼠标灵敏度乘子 = 原视野/当前视野，动画过程中同步平滑变化。 */
    public double getSensitivityScale() {
        return this.lastFov / Math.max(this.currentFov, 1.0);
    }
}
