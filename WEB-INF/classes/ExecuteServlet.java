/*
    ==============================================================================
                    --------- EXECUTESERVLET.JAVA---------
        Unless required by applicable law or agreed to in writing, software
        distributed under the License is distributed on an "AS IS" BASIS,
        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
        See the License for the specific language governing permissions and
        limitations under the License.
    ==============================================================================

    Following nomenclature for the Machine Learning models has been followed:

    "Nearest Mean Classifier"   =   nmc
    "K Nearest Neighbors"       =   knn
    "MultiLayer Perceptron"     =   mlp
    "AutoEncoders"              =   aen
    "Linear SVM"                =   svm
    "Self Optimizing SVM"       =   sosvm
    "Random Forest"             =   rff
    "Random Tree"               =   rnt

 */

import net.sf.javaml.classification.Classifier;
import net.sf.javaml.core.DenseInstance;
import net.sf.javaml.core.Instance;
import org.apache.commons.io.FileUtils;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.opencv.core.*;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import static java.lang.Math.max;
import static org.opencv.core.Core.mean;

public class ExecuteServlet extends HttpServlet {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            response.setContentType("text/html");
            System.out.println("In ExecuteServlet");

            String mType = request.getParameter("mType");
            System.out.println("mType: " + mType);
            String[] lister = new String[]{"Nearest Mean Classifier", "K Nearest Neighbors", "MultiLayer Perceptron", "AutoEncoders", "Linear SVM", "Self Optimizing SVM", "Random Forest", "Random Tree"};
            String modelName;
            switch (mType) {
                case "mlp":
                    modelName = lister[2];
                    break;
                case "sosvm":
                    modelName = lister[5];
                    break;
                case "nmc":
                    modelName = lister[0];
                    break;
                case "knn":
                    modelName = lister[1];
                    break;
                case "anc":
                    modelName = lister[3];
                    break;
                case "svm":
                    modelName = lister[4];
                    break;
                case "rff":
                    modelName = lister[6];
                    break;
                case "rnt":
                    modelName = lister[7];
                    break;
                default: //Materialize.toast("Wrong Model passed",6000);
                    modelName = null;
            }
            System.out.println("Model Requested: " + modelName);
            String imgName = request.getParameter("imgName");
            System.out.println("imgName: " + imgName);
            String[] imgNames = imgName.split("\\|\\|");
            System.out.println("Image(s) selected: ");
            String pathname, threshFilename, outputFilename, whiteNormalFilename;
            PrintWriter out = response.getWriter();
            String docstart = "<!DOCTYPE html><html lang=\"en\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/><meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1.0\"/><meta name=\"theme-color\" content=\"#5e42a6\"><title>ML for BMI</title><link rel=\"icon\" type=\"icon/ico\" href=\"rsc/icon.ico\"/><link href=\"https://fonts.googleapis.com/icon?family=Material+Icons\" rel=\"stylesheet\"><link href=\"css\\materialize.css\" type=\"text/css\" rel=\"stylesheet\" media=\"screen,projection\"/><link href=\"css\\style.css\" type=\"text/css\" rel=\"stylesheet\" media=\"screen,projection\"/><link href=\"css\\dropify.css\" type=\"text/css\" rel=\"stylesheet\" media=\"screen,projection\"/><link href=\"css\\imgcenter.css\" type=\"text/css\" rel=\"stylesheet\" media=\"screen,projection\"/></head><body><div class=\"navbar-fixed\"><nav class=\"blue darken-3\" role=\"navigation\"><div class=\"row\"><div class=\"nav-wrapper container col s12\"><div class=\"nav-wrapper container col s12\"><a id=\"logo-container\" href=\"index.html\" class=\"brand-logo\"><i class=\"material-icons \" style=\"font-size: 35px\">polymer</i></a></div></div></div></nav></div><div class=\"parallax-container valign-wrapper hero \"><div class=\"container\"><div class=\"row center\"><h5 class=\"header col s12 light\"><br><br>Classification Result: " + modelName + " <br><br><br></h5></div>";
            String docend = "<div class=\"parallax\"><img src=\"rsc/lawn.jpg\" alt=\"rsc/lawn.jpg\"></div></div></div><div class=\"section z-depth-5 white  darken-3\"><div class=\"container\"><div class=\"row\"><div class=\"col s12 m12\"><div class=\"icon-block\"><h5 class=\"center light\">ML for BMI</h5><h2 class=\"center light-blue-text\"><a href=\"index.html\" ><i class=\"material-icons indigo-text\" style=\"font-size: 90px\">polymer</i></a></h2><h5 class=\"light\" align='center'>Using <b>Machine Learning</b> to solve Bio-Medical Imaging problems<br></h5></div></div></div></div></div><div class=\"parallax-container valign-wrapper\"><div class=\"container\"><h5 class=\"light\" align=\"center\"><br><br>Close this <b>page</b> to try other models </h5></div><div class=\"parallax\"><img src=\"rsc/lawn.jpg\" alt=\"Unsplashed background img 2\"></div></div><footer class=\"page-footer red darken-1\"><div class=\"container\"><div class=\"row center\"><div class=\"col s12\"><a id=\"logo-container\" href=\"index.html\" class=\"brand-logo\"><img src=\"rsc/logo_small.png\" width=\"100\"></a><h5 class=\"white-text\">Machine Learning for Bio Medical Imaging</h5><p class=\"grey-text text-lighten-4\">Using Machine Learning models to solve problems in BioMedical Imaging</p></div></div></div><div class=\"footer-copyright\"><div class=\"container\"><a class=\"red-text text-lighten-3\" href=\"#!\">BioMedical Imaging Informatics, SMST</a> @IITKGP 2016</div></div></footer></body><script src=\"js/jquery-2.2.2.min.js\"></script><script src=\"js/materialize.js\"></script><script src=\"js/init.js\"></script><script src=\"js/dropify.js\"></script><script>$(document).ready(function() {$('.parallax').parallax();});</script><script>!function(n,r){\"function\"==typeof define&&define.amd?define(r):\"object\"==typeof exports?module.exports=r():n.transformicons=r()}(this||window,function(){\"use strict\";var n={},r=\"tcon-transform\",t={transform:[\"click\"],revert:[\"click\"]},e=function(n){return\"string\"==typeof n?Array.prototype.slice.call(document.querySelectorAll(n)):\"undefined\"==typeof n||n instanceof Array?n:[n]},o=function(n){return\"string\"==typeof n?n.toLowerCase().split(\" \"):n},f=function(n,r,f){var c=(f?\"remove\":\"add\")+\"EventListener\",u=e(n),s=u.length,a={};for(var l in t)a[l]=r&&r[l]?o(r[l]):t[l];for(;s--;)for(var d in a)for(var v=a[d].length;v--;)u[s][c](a[d][v],i)},i=function(r){n.toggle(r.currentTarget)};return n.add=function(r,t){return f(r,t),n},n.remove=function(r,t){return f(r,t,!0),n},n.transform=function(t){return e(t).forEach(function(n){n.classList.add(r)}),n},n.revert=function(t){return e(t).forEach(function(n){n.classList.remove(r)}),n},n.toggle=function(t){return e(t).forEach(function(t){n[t.classList.contains(r)?\"revert\":\"transform\"](t)}),n},n});</script></html>";
            //Creating Document

            // WebApp Abs. Path: "C:/apache-tomcat-8.0.36/webapps/MachineLearningIITKGP/"

            String fullpath = getServletContext().getRealPath("/");
            out.println(docstart);
            for (String name : imgNames) {


                pathname = "uploads/" + name;
                System.out.println("Path name: " + pathname);

                //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Reading Uploaded Image~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

                Mat og_inputImg = Highgui.imread(fullpath + pathname, Highgui.CV_LOAD_IMAGE_COLOR);
                System.out.println("Sample Pixel vals: " + og_inputImg.get(0, 0)[0]);

                //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Image Pre-Processing Begins~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

                //~~~~~~~~~~~~~~~~~~~~~~ Grey world normalization~~~~~~~~~~~~~~
                Mat inputImg= GNormImg(og_inputImg);
                Imgproc.cvtColor(inputImg, inputImg, Imgproc.COLOR_BGR2RGB);
                //~~~~~~~~~~~~~~~~~~~~~~ BiLateral Filtering~~~~~~~~~~~~~~~~~~~
                int height = inputImg.rows();
                int width =  inputImg.cols();
                Mat inputImg_temp = new Mat();

                Imgproc.bilateralFilter(inputImg,inputImg_temp,(max(height,width))/100,40.0,(max(height,width))/20);
                // ~~~~~~~~~~~~~~~~~~~~~~Adaptive Thresholding, Canny edge detection, Dilation & medianBlurring~~~~~~~~~~~~~~~~~~~~~~
                Mat thresholded = new Mat();
                int win= (int)(((float)(((height+width)/2)))/20.0);
                win = 2*((int)(win/2))+1;

                Vector<Mat> rgb_planes = new Vector<>();
                Core.split(inputImg_temp, rgb_planes);

                Imgproc.adaptiveThreshold(rgb_planes.get(2),thresholded,255,Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C,Imgproc.THRESH_BINARY_INV,win,1);
                threshFilename = "output/" + name.substring(0, name.indexOf('.')) + "_thresh_adap" + name.substring(name.indexOf('.'));
                Highgui.imwrite(fullpath + threshFilename, thresholded);
                System.out.println("After adaptivethresholding, Thresholded Image Saved. At: " + fullpath + threshFilename);

                Mat edges= new Mat();
                Mat element;
                Imgproc.Canny(rgb_planes.get(2),edges,5,50);

                threshFilename = "output/" + name.substring(0, name.indexOf('.')) + "_edges" + name.substring(name.indexOf('.'));
                Highgui.imwrite(fullpath + threshFilename, edges);
                System.out.println("After CannyEdge, Edges Image Saved. At: " + fullpath + threshFilename);

                element = Imgproc.getStructuringElement(Imgproc.MORPH_CROSS, new Size(2,2),new Point(-1,-1));
                Imgproc.dilate(edges,edges,element);

                threshFilename = "output/" + name.substring(0, name.indexOf('.')) + "_edges_dilated" + name.substring(name.indexOf('.'));
                Highgui.imwrite(fullpath + threshFilename, edges);

                Core.multiply(edges, Scalar.all(255),edges);

                threshFilename = "output/" + name.substring(0, name.indexOf('.')) + "_edges_dilated_scaled" + name.substring(name.indexOf('.'));
                Highgui.imwrite(fullpath + threshFilename, edges);

                Core.subtract(thresholded,edges,thresholded);

                threshFilename = "output/" + name.substring(0, name.indexOf('.')) + "_thresh_subtract" + name.substring(name.indexOf('.'));
                Highgui.imwrite(fullpath + threshFilename, thresholded);
                System.out.println("After subtraction Image Saved. At: " + fullpath + threshFilename);

                Imgproc.erode(thresholded,thresholded,element);

                threshFilename = "output/" + name.substring(0, name.indexOf('.')) + "_thresh_erosion" + name.substring(name.indexOf('.'));
                Highgui.imwrite(fullpath + threshFilename, thresholded);
                System.out.println("After Erosion Image Saved. At: " + fullpath + threshFilename);
                int winM = 2 * ( (width/300)) + 1;
                Imgproc.medianBlur(thresholded,thresholded,winM);

                threshFilename = "output/" + name.substring(0, name.indexOf('.')) + "_thresh_mblur" + name.substring(name.indexOf('.'));
                Highgui.imwrite(fullpath + threshFilename, thresholded);
                System.out.println("AFter Median Blurring Image Saved. At: " + fullpath + threshFilename);

                // ------------------------------------------- Image Processing ends here -----------------------------------

                whiteNormalFilename = "output/" + name.substring(0, name.indexOf('.')) + "_whiteNormal" + name.substring(name.indexOf('.'));
                Highgui.imwrite(fullpath + whiteNormalFilename, inputImg);
                System.out.println("White Normalized Image Saved. At: " + fullpath + whiteNormalFilename);
                threshFilename = "output/" + name.substring(0, name.indexOf('.')) + "_thresh" + name.substring(name.indexOf('.'));
                Highgui.imwrite(fullpath + threshFilename, thresholded);
                System.out.println("Thresholded Image Saved. At: " + fullpath + threshFilename);
                outputFilename = "output/" + name.substring(0, name.indexOf('.')) + "_out_" + mType + name.substring(name.indexOf('.'));
                Mat finalOutputImg = new Mat(2 * inputImg.rows() + inputImg.rows() / 100, inputImg.cols(), inputImg.type());
                System.out.println("Creating new outputImg");
                Mat tempOutputImg = new Mat(inputImg.rows(), inputImg.cols(), inputImg.type());
                Mat filler = Mat.zeros(inputImg.rows() / 100, inputImg.cols(), inputImg.type());
                Mat outFiller = new Mat(inputImg.rows() + inputImg.rows() / 100, inputImg.cols(), inputImg.type());

                if (mType.equals("nmc") || mType.equals("knn") || mType.equals("nvb") || mType.equals("svm") || mType.equals("sosvm") || mType.equals("rbfsvm") || mType.equals("rff") || mType.equals("rnt")) {
                    System.out.println("Using JavaML Classifier....");
                    Classifier model;
                    InputStream is = new ObjectInputStream(
                            new FileInputStream(fullpath + "models/" + mType + ".bin"));
                    ObjectInputStream ois = (ObjectInputStream) is;
                    model = (Classifier) ois.readObject();
                    System.out.println("Loaded Classifier Model....");
                    Instance inst;
                    double arr[];

                    for (int i = 0; i < inputImg.rows(); i++) {
                        for (int j = 0; j < inputImg.cols(); j++) {

                            if (thresholded.get(i, j)[0] > 0) {
                                arr = inputImg.get(i, j);
                                inst = new DenseInstance(new double[]{arr[2], arr[1], arr[0]});
                                int predicted = Integer.parseInt(model.classify(inst).toString());
                                if (predicted == 1) {
                                    tempOutputImg.put(i, j, new double[]{255, 0, 0});
                                } else tempOutputImg.put(i, j, new double[]{0, 0, 255});
                            } else tempOutputImg.put(i, j, inputImg.get(i, j));

                        }
                    }
                    System.out.println("Output Image Created..");
                    //finalOutputImg.rowRange(0,inputImg.rows()-1).colRange(0,inputImg.cols()-1)=inputImg;
                    //finalOutputImg.rowRange(inputImg.rows(),2*inputImg.rows()-1).colRange(0,inputImg.cols()-1)=tempOutputImg;

                    Core.vconcat(Arrays.asList(og_inputImg, filler), outFiller);
                    Core.vconcat(Arrays.asList(outFiller, tempOutputImg), finalOutputImg);

                    System.out.println("Mapping to Final Output");

                } else {

                    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Reading DeepLearning4j Models here~~~~~~~~~~~~~~~~~~~~~

                    System.out.println("Model selected is: " + modelName + ". " + "(" + mType + ")");
                    MultiLayerConfiguration confFromJson = MultiLayerConfiguration.fromJson(FileUtils.readFileToString(new File(fullpath + "models/" + mType + ".json")));
                    DataInputStream dis = new DataInputStream(new FileInputStream(fullpath + "models/" + mType + ".bin"));
                    INDArray newParams = Nd4j.read(dis);
                    dis.close();
                    MultiLayerNetwork model = new MultiLayerNetwork(confFromJson);
                    model.init();
                    model.setParams(newParams);
                    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Model recreated~~~~~~~~~~~~~~~~~~~~~~~~~~~~

                    System.out.println("Model Re-created from stored .json and .bin files");

                    //~~~~~~~~~~~~~~~~~~~~~~~~Model Function on Image: Model interprets Image by classifying each pixel of the image~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    INDArray predicted;
                    int max_prob, k, max_class;
                    for (int i = 0; i < inputImg.rows(); i++) {
                        for (int j = 0; j < inputImg.cols(); j++) {

                            if (thresholded.get(i, j)[0] > 0) {

                                predicted = model.output(Nd4j.create(new double[]{inputImg.get(i, j)[2], inputImg.get(i, j)[1], inputImg.get(i, j)[0]}), false);
                                max_prob = predicted.getInt(0);
                                max_class = 0;
                                k = 0;
                                while (k < 2) {
                                    if (max_prob < predicted.getInt(k)) {
                                        max_prob = predicted.getInt(k);
                                        max_class = k;
                                    }
                                    k++;
                                }
                                max_class++;
                                if (max_class == 1) {
                                    tempOutputImg.put(i, j, new double[]{0, 0, 255});
                                } else tempOutputImg.put(i, j, new double[]{255, 0, 0});

                            } else tempOutputImg.put(i, j, inputImg.get(i, j));


                        }
                    }
                    System.out.println("Output Image Created..");

                    Core.vconcat(Arrays.asList(og_inputImg, filler), outFiller);
                    Core.vconcat(Arrays.asList(outFiller, tempOutputImg), finalOutputImg);

                    System.out.println("Mapping to Final Output");

                }
                //~~~~~~~~~~~~~~~Writing Output Image~~~~~~~~~~~~~~~~~~~
                Highgui.imwrite(fullpath + outputFilename, finalOutputImg);
                System.out.println("Output Image written to: " + fullpath + outputFilename);
                System.out.println("~~~~~~~~MODEL EXECUTION COMPLETED~~~~~~~\n\n");

                //~~~~~~~~~~~~~~~~Writing Image HTML Script to Pop-Up Page~~~~~~~~~~~~~~~~~~~~~~~~
                out.println("<div class=\"row\" align=\"center\"><img class=\"display materialboxed\" width=\"400\" align=\"center\" style=\"float:center\" src=\"" + outputFilename + "\"></div>");

            }

            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~Printing Pop-Up Page-HTML/JavaScript here~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            out.println(docend);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception found in main doGet method");
        }

    }

    public Mat AdaptSegmentImg(Mat inputImg) {
        Mat result = new Mat();
        ArrayList<Mat> rgb_planes = new ArrayList<>();
        Core.split(inputImg, rgb_planes);
        int win = inputImg.cols() / 20;
        win = 2 * ((win / 2)) + 1;
        Imgproc.adaptiveThreshold(rgb_planes.get(1), result, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY_INV, win, 1);
        return result;
    }

    public Mat GNormImg(Mat Img) {
        System.out.println("Default Image Depth: "+Img.depth());
        Mat ImgGr = new Mat();
        Imgproc.cvtColor(Img, ImgGr, Imgproc.COLOR_BGR2GRAY);
        Vector<Mat> rgb_planes = new Vector<>();
        Core.split(Img, rgb_planes);
        Mat B,G,R,Gr;
        B = new Mat();
        G = new Mat();
        R = new Mat();
        Gr= new Mat();
        rgb_planes.get(0).convertTo(B,CvType.CV_32F);
        rgb_planes.get(1).convertTo(G,CvType.CV_32F);
        rgb_planes.get(2).convertTo(R,CvType.CV_32F);
        ImgGr.convertTo(Gr,CvType.CV_32F);

        double M_B = (double) mean(B).val[0];
        double M_G = (double) mean(G).val[0];
        double M_R = (double) mean(R).val[0];
        double M_Gr = (double) mean(Gr).val[0];
        Core.multiply(B, Scalar.all((M_Gr / M_B)), B);
        Core.multiply(G, Scalar.all((M_Gr / M_G)), G);
        Core.multiply(R, Scalar.all((M_Gr / M_R)), R);
        Mat B_ =  new Mat();
        Mat G_ = new Mat();
        Mat R_ = new Mat();
        B.convertTo(B_,CvType.CV_8U);
        G.convertTo(G_,CvType.CV_8U);
        R.convertTo(R_,CvType.CV_8U);

        Vector<Mat> merge = new Vector<>();
        merge.add(0, B_);
        merge.add(1, G_);
        merge.add(2, R_);
        Core.merge(merge, Img);
        Mat ImgGNorm = new Mat();
        Imgproc.cvtColor(Img, ImgGNorm, Imgproc.COLOR_BGR2RGB);
        return ImgGNorm;
    }

}
