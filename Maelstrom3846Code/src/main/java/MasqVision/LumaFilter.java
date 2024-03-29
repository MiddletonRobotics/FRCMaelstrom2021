package MasqVision;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 * Created by Keval Kataria on 3/6/2021
 */

public class LumaFilter extends MasqCVColorFilter {
    private final Scalar lower;

    public LumaFilter(int lower) {
        this.lower = new Scalar(lower);
    }

    @Override
    public void process(Mat input, Mat mask) {
        Imgproc.cvtColor(input, input, Imgproc.COLOR_RGB2YCrCb);

        Imgproc.GaussianBlur(input, input, new Size(5, 5), 0);
        Core.inRange(input, lower, new Scalar(255), mask);
        input.release();
    }
}