package com.example.lyy.demo.chart;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.example.lyy.entity.Serie;
import com.example.lyy.util.jfreechart.ChartUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @date 2014-6-11
 *       <p>
 *       创建图表步骤：<br/>
 *       1：创建数据集合<br/>
 *       2：创建Chart：<br/>
 *       3:设置抗锯齿，防止字体显示不清楚<br/>
 *       4:对柱子进行渲染，<br/>
 *       5:对其他部分进行渲染<br/>
 *       6:使用chartPanel接收<br/>
 *
 *       </p>
 */
public class StackedBarChart {

	public StackedBarChart() {
	}

	public DefaultCategoryDataset createDataset() {
		// 标注类别
		String[] categories = { "流量管理","安全提示","消防系统","治安管理","交通管理","环境卫生","应急救助","供配电","安全防护","灭火器","食品安全","逃生用具","安全设备","经营许可","人员培训","特种设备","年检记录","现场巡查"};
		Vector<Serie> series = new Vector<Serie>();
		// 柱子名称：柱子所有的值集合
		series.add(new Serie("共计检查项", new Double[] { 4.9, 7.5, 16.4, 19.2, 14.0, 17.0, 13.6, 14.5, 21.4, 14.1, 9.6,14.2,12.0,16.2,12.8,13.9,10.5,9.1 }));
		series.add(new Serie("不符合项", new Double[] { 0.6, 0.8, 0.5, 1.4, 2.0, 1.5, 1.0, 1.3, 3.2, 2.5, 1.6, 3.3,1.6,0.6,1.4,3.2,2.1,0.2 }));
		// 1：创建数据集合
		DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);
		return dataset;
	}

	public ChartPanel createChart() throws IOException {
		// 2：创建Chart[创建不同图形]
		JFreeChart chart = ChartFactory.createStackedBarChart("河南省黄金寨原生态旅游区隐患排查情况统计图", "", "", createDataset());
		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(new Font("宋体",Font.PLAIN,20));
		//标签字体
		chart.getLegend().setItemFont(new Font("宋体",Font.PLAIN,15));
		//chart.getLegend().setItemFont(GetFontUtil.getFont(Font.PLAIN,15f));
		// 3:设置抗锯齿，防止字体显示不清楚
		ChartUtils.setAntiAlias(chart);// 抗锯齿
		// 4:对柱子进行渲染[创建不同图形]
		ChartUtils.setStackBarRender(chart.getCategoryPlot());
		// 5:对其他部分进行渲染
		ChartUtils.setXAixs(chart.getCategoryPlot());// X坐标轴渲染
		ChartUtils.setYAixs(chart.getCategoryPlot());// Y坐标轴渲染
		chart.setTextAntiAlias(true);
		// 设置标注无边框
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		// 6:使用chartPanel接收
		ChartPanel chartPanel = new ChartPanel(chart);
		System.out.println(System.getProperty("java.io.tmpdir")+"book.jpg");
		FileOutputStream fos = new FileOutputStream(System.getProperty("java.io.tmpdir")+"book.jpg");
		//用ChartUtilities工具输出
		ChartUtilities.writeChartAsJPEG(fos,chart,556,300);
		fos.close();
		return chartPanel;
	}

	public static void main(String[] args) {
        String folder=System.getProperty("java.io.tmpdir");
        System.out.println(folder);
		final JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1024, 420);
		frame.setLocationRelativeTo(null);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// 创建图形
				ChartPanel chartPanel = null;
				try {
					chartPanel = new StackedBarChart().createChart();
				} catch (IOException e) {
					e.printStackTrace();
				}
				frame.getContentPane().add(chartPanel);
				frame.setVisible(true);
			}
		});

	}

}
