package com.github.kittinunf.statik.viewholder

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.widget.TextViewCompat
import android.support.v7.app.AppCompatDialogFragment
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.github.kittinunf.statik.R
import com.github.kittinunf.statik.representable.DateRowRepresentable
import java.util.*
import java.util.Calendar.DATE
import java.util.Calendar.MONTH
import java.util.Calendar.YEAR

class DateRowViewHolder(view: View) : StatikViewHolder(view), BindableViewHolder<DateRowRepresentable> {

    var isFirstTime = false

    override fun bind(item: DateRowRepresentable) {
        item.onViewSetupListener?.invoke(itemView)

        val titleTextView = itemView.findViewById<TextView>(R.id.statik_row_text)
        titleTextView.text = item.text

        val textSetup = item.onTextSetupListener
        if (textSetup == null) {
            TextViewCompat.setTextAppearance(titleTextView, R.style.Widget_Statik_Base_PrimaryTextItem)
        } else {
            textSetup(titleTextView)
        }

        val dateEditText = itemView.findViewById<EditText>(R.id.statik_date_text)
        dateEditText.apply {
            hint = item.hint
            val fragmentActivity = (itemView.context as? FragmentActivity)
            setOnClickListener {
                fragmentActivity?.let { activity ->
                    val currentDateValue = Calendar.getInstance().apply { set(item.year, item.month, item.dayOfMonth) }
                    val startingDate =
                            //first time, we check starting date configuration, if any. Subsequently, we always use current value
                            if (isFirstTime) {
                                item.startingDate ?: currentDateValue
                            } else {
                                currentDateValue
                            }

                    val datePickerDialog = createDatePickerFragment(startingDate, dateEditText, item)
                    datePickerDialog.show(activity.supportFragmentManager, DatePickerFragment::class.java.simpleName)
                }
            }
        }

        //click
        item.onClickListener?.let { listener ->
            itemView.setOnClickListener { view ->
                listener.invoke(view, adapterPosition)
            }
        }
    }

    private fun createDatePickerFragment(startingDate: Calendar?,
                                         dateEditText: EditText,
                                         item: DateRowRepresentable): AppCompatDialogFragment =
            DatePickerFragment.newInstance(startingDate)
                    .apply {
                        dateSelectedListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                            item.also {
                                it.year = year
                                it.month = month
                                it.dayOfMonth = dayOfMonth
                                it.dateFormatter?.let {
                                    dateEditText.setText(it.format(Calendar.getInstance()
                                            .apply { set(year, month, dayOfMonth) }.time))
                                }
                            }.run { onDateSelectedListener?.invoke(dateEditText, year, month, dayOfMonth) }
                        }
                    }

    class DatePickerFragment : AppCompatDialogFragment() {

        var dateSelectedListener: DatePickerDialog.OnDateSetListener? = null

        companion object {
            private const val StartingDate = "STARTING_DATE"

            fun newInstance(startingDate: Calendar? = null) = DatePickerFragment().apply {
                val bundle = Bundle()
                bundle.putSerializable(StartingDate, startingDate)
                arguments = bundle
            }
        }

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val startingDate = (arguments?.getSerializable(StartingDate) as? Calendar)
                    ?: Calendar.getInstance()

            return DatePickerDialog(activity,
                    dateSelectedListener,
                    startingDate.get(YEAR),
                    startingDate.get(MONTH),
                    startingDate.get(DATE))
        }
    }
}