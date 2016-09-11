package com.pulse.poll;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;

public class ChartFactory {
    public byte[] createBarChart(Collection<AnswerCountPair> pairs) {
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        pairs.forEach(pair -> dataSet.addValue((Number)pair.getCount(), pair.getAnswer(), ""));

        JFreeChart chart = org.jfree.chart.ChartFactory.createBarChart(
                "Ответы на вопросы",
                "Номер ответа",
                "Количество ответов",
                dataSet,
                PlotOrientation.HORIZONTAL,
                true,
                true,
                false
        );

        chart.setBackgroundPaint(Color.white);

        BufferedImage objBufferedImage = chart.createBufferedImage(300, 200);
        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        try {
            ImageIO.write(objBufferedImage, "png", bas);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bas.toByteArray();
    }
}