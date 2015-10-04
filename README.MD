![Screenshot](https://raw.githubusercontent.com/ShinChven/DonutProgressView/master/screenshot/screenshot.jpg)<p>

#xml usage
<p>
``` xml
     <com.github.ShinChven.widget.DonutProgressView
                android:layout_centerInParent="true"
                android:id="@+id/my_donut_progress"
                app:lineWidth="7dp"
                app:maxProgress="300"
                app:progress="125"
                app:text="125/300"
                app:textColor="#fff"
                app:textSize="18sp"
                android:layout_width="100dp"
                android:layout_height="100dp"/>
```

#styleable

``` xml
    <declare-styleable name="zagyou_circle_progress">
        <attr name="text" format="string"></attr>
        <attr name="textColor" format="color"></attr>
        <attr name="textSize" format="dimension"></attr>
        <attr name="progress" format="integer"></attr>
        <attr name="maxProgress" format="integer"></attr>
        <attr name="progressBackgroundColor" format="color"></attr>
        <attr name="progressColor" format="color"></attr>
        <attr name="lineWidth" format="dimension"></attr>
        <attr name="progressMargin" format="dimension"></attr>
    </declare-styleable>
```