package com.bitliker.simple.modular.scheduler

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import com.bitliker.simple.R
import com.bitliker.simple.common.BaseActivity
import com.bitliker.simple.modular.scheduler.model.Schedule
import com.bitliker.simple.modular.scheduler.utils.ScheduleUtils
import kotlinx.android.synthetic.main.activity_scheduler.*
import java.util.*

class SchedulerTestActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scheduler)
        addBtm.setOnClickListener(mOnClickListener)
        deleteBtn.setOnClickListener(mOnClickListener)
        findBtn.setOnClickListener(mOnClickListener)
    }

    private var mOnClickListener=View.OnClickListener {v->
        when (v.id) {
            R.id.addBtm -> {
                doAdd()
            }
            R.id.findBtn -> {
                var dp=DatePickerDialog(ct,mOnDateSetListener,2018,8,31)
                dp.show()
            }
            R.id.deleteBtn -> {
                doDelete()
            }
        }
    }

    private var mOnDateSetListener= DatePickerDialog.OnDateSetListener{ datePicker: DatePicker, year: Int, month: Int, day: Int ->
        doFind(year,month,day)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1) {

        }
    }

    fun doAdd() {
        var mSchedule = Schedule()
        mSchedule.address = "这里是地址"
        mSchedule.allDay = 1
        mSchedule.repeat = "工作"
        mSchedule.id = 1
        mSchedule.title = "这个是标题"
        mSchedule.tag = "这个是这个是标签"
        mSchedule.remarks = "这个是备注"
        mSchedule.startTime = System.currentTimeMillis() + 20 * 60 * 1000
        mSchedule.endTime = System.currentTimeMillis() + 320 * 60 * 1000
        mSchedule.warnTime = 15
        mSchedule.address = "这个是地点"
        ScheduleUtils.addCalendarEvent(this, mSchedule)
    }

    fun doFind(year: Int, month: Int, day: Int) {
        val cal = Calendar.getInstance()
        cal.timeZone = TimeZone.getTimeZone("UTC+8")
        cal.set(year,month,day)
        cal.set(Calendar.HOUR, 0)
        cal.set(Calendar.SECOND, 1)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.MILLISECOND, 0)
        val startTime = cal.timeInMillis
        val endTime = cal.timeInMillis + 24 * 60 * 60 * 1000
        ScheduleUtils.getSystemCalendar(this,startTime,endTime)
//        ScheduleUtils.loadCalendarAccount(this)
    }

    fun doDelete() {
        ScheduleUtils.deleteSystemCalendar(this, 1)
    }
}
