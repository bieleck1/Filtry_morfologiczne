
import java.awt.image.BufferedImage;

public class Filters {

    static BufferedImage erosion(BufferedImage image, boolean[][] structuralMatrix) {
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < newImage.getWidth(); i++) {
            for (int j = 0; j < newImage.getHeight(); j++) {
                int newR = 255;
                int newG = 255;
                int newB = 255;
                for (int a = -2; a <= 2; a++) {
                    for (int b = -2; b <= 2; b++) {
                        if (structuralMatrix[a + 2][b + 2]) {
                            try {
                                if (getR(image.getRGB(i + a, j + b)) < newR) {
                                    newR = getR(image.getRGB(i + a, j + b));
                                }
                                if (getG(image.getRGB(i + a, j + b)) < newG) {
                                    newG = getG(image.getRGB(i + a, j + b));
                                }
                                if (getB(image.getRGB(i + a, j + b)) < newB) {
                                    newB = getB(image.getRGB(i + a, j + b));
                                }
                            } catch (ArrayIndexOutOfBoundsException iHaveBiggerMatrix) {
                            }
                        }
                    }
                }
                newImage.setRGB(i, j, transformToRGB(newR, newG, newB));
            }
        }
        return newImage;
    }

    static BufferedImage dilatation(BufferedImage image, boolean[][] structuralMatrix) {
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < newImage.getWidth(); i++) {
            for (int j = 0; j < newImage.getHeight(); j++) {
                int newR = 0;
                int newG = 0;
                int newB = 0;
                for (int a = -2; a <= 2; a++) {
                    for (int b = -2; b <= 2; b++) {
                        if (structuralMatrix[a + 2][b + 2]) {
                            try {
                                if (getR(image.getRGB(i + a, j + b)) > newR) {
                                    newR = getR(image.getRGB(i + a, j + b));
                                }
                                if (getG(image.getRGB(i + a, j + b)) > newG) {
                                    newG = getG(image.getRGB(i + a, j + b));
                                }
                                if (getB(image.getRGB(i + a, j + b)) > newB) {
                                    newB = getB(image.getRGB(i + a, j + b));
                                }
                            } catch (ArrayIndexOutOfBoundsException iHaveBiggerMatrix) {
                            }
                        }
                    }
                }
                newImage.setRGB(i, j, transformToRGB(newR, newG, newB));
            }
        }
        return newImage;
    }
    
    static BufferedImage contouring(BufferedImage image, boolean[][] structuralMatrix) {
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < newImage.getWidth(); i++) {
            for (int j = 0; j < newImage.getHeight(); j++) {
                int newR = 0;
                int newG = 0;
                int newB = 0;
                for (int a = -2; a <= 2; a++) {
                    for (int b = -2; b <= 2; b++) {
                        if (structuralMatrix[a + 2][b + 2]) {
                            try {
                                if (getR(image.getRGB(i + a, j + b)) > newR) {
                                    newR = getR(image.getRGB(i + a, j + b));
                                }
                                if (getG(image.getRGB(i + a, j + b)) > newG) {
                                    newG = getG(image.getRGB(i + a, j + b));
                                }
                                if (getB(image.getRGB(i + a, j + b)) > newB) {
                                    newB = getB(image.getRGB(i + a, j + b));
                                }
                            } catch (ArrayIndexOutOfBoundsException iHaveBiggerMatrix) {
                            }
                        }
                    }
                }
                
                newR -= getR(image.getRGB(i, j));
                newG -= getG(image.getRGB(i, j));
                newB -= getB(image.getRGB(i, j));
                
                newImage.setRGB(i, j, transformToRGB(newR, newG, newB));
            }
        }
        return newImage;
    }

    public static BufferedImage binarization(BufferedImage image) {
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < newImage.getWidth(); i++) {
            for (int j = 0; j < newImage.getHeight(); j++) {
                int imageRGB = image.getRGB(i, j);
                int binaryR = 0;
                int binaryG = 0;
                int binaryB = 0;
                int isBinaryR = 0;
                int isBinaryG = 0;
                int isBinaryB = 0;
                if (getR(imageRGB) > 127) {
                    isBinaryR++;
                } else {
                    isBinaryR--;
                }
                if (getG(imageRGB) > 127) {
                    isBinaryG++;
                } else {
                    isBinaryG--;
                }
                if (getB(imageRGB) > 127) {
                    isBinaryB++;
                } else {
                    isBinaryB--;
                }

                if (isBinaryR > 0) {
                    binaryR = 255;
                }
                if (isBinaryG > 0) {
                    binaryG = 255;
                }
                if (isBinaryB > 0) {
                    binaryB = 255;
                }

                newImage.setRGB(i, j, transformToRGB(binaryR, binaryG, binaryB));
            }
        }
        return newImage;
    }

    private static int getR(int in) {
        return (int) (in >> 16) & 0xff;
    }

    private static int getG(int in) {
        return (int) (in >> 8) & 0xff;
    }

    private static int getB(int in) {
        return (int) in & 0xff;
    }

    private static int transformToRGB(int r, int g, int b) {
        return (int) ((((r << 8) | g) << 8) | b);
    }
}
