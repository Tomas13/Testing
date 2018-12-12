package kz.senim.ui.dialog.type

import android.app.AlertDialog
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import kz.senim.common.extensions.inflate
import rx.functions.Action1
import android.text.InputFilter
import kotlinx.android.synthetic.main.dialog_text_field.view.*
import kotlinx.android.synthetic.main.widget_select.view.*
import malmalimet.kz.R
import malmalimet.kz.ui.widget.dialog.type.SimpleTextWatcher
import malmalimet.kz.ui.widget.validators.Validator


class TextFieldDialog : BaseDialog(), Dialog {

    private var extraMessage: String? = null
    private var hint: String? = null
    private var initialValue: String? = null
    private var inputType = InputType.TYPE_CLASS_TEXT
    private var onSubmit: ((String) -> Unit)? = null
    private var validator: Validator<String>? = null
    private var imeOptions: Int = 0
    private var maxLength: Int = -1

    private var extraButtonCallback: Action1<Dialog>? = null
    private var extraButtonTextRes: Int = 0

    private var textWatcher: TextWatcher? = null

    private lateinit var dialog: AlertDialog

    private lateinit var editText: EditText
    private lateinit var errorMessageView: TextView

    private val onEditorActionListener = TextView.OnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            submit()
            true
        } else {
            false
        }
    }

    private val onSubmitBtnClickListener = View.OnClickListener { submit() }
    private val onExtraBtnClickListener: View.OnClickListener by lazy(LazyThreadSafetyMode.NONE) {
        View.OnClickListener { extraButtonCallback?.call(this) }
    }

    override fun setTitle(title: String?): TextFieldDialog {
        super.setTitle(title)
        return this
    }

    fun setExtraMessage(extraMessage: String): TextFieldDialog {
        this.extraMessage = extraMessage
        return this
    }

    fun setHint(hint: String): TextFieldDialog {
        this.hint = hint
        return this
    }

    fun setInitialValue(initialValue: String?): TextFieldDialog {
        this.initialValue = initialValue
        return this
    }

    fun setInputType(inputType: Int): TextFieldDialog {
        this.inputType = inputType
        return this
    }

    fun setCallback(onSubmit: (String) -> Unit): TextFieldDialog {
        this.onSubmit = onSubmit
        return this
    }

    fun setValidator(validator: Validator<String>): TextFieldDialog {
        this.validator = validator
        return this
    }

    fun setImeOptions(options: Int): TextFieldDialog {
        imeOptions = options
        return this
    }

    fun setMaxLength(length: Int): TextFieldDialog {
        maxLength = length
        return this
    }

    fun setExtraButton(extraButtonTextRes: Int, extraButtonCallback: Action1<Dialog>): TextFieldDialog {
        this.extraButtonTextRes = extraButtonTextRes
        this.extraButtonCallback = extraButtonCallback
        return this
    }

    override fun show() {
        val builder = makeBuilder()
        builder.setTitle(title)

        val root = context.inflate(R.layout.dialog_text_field)

        val extraMessageView = root.extra_message
        editText = root.edit_text1
        errorMessageView = root.error_message

        configureExtraMessageView(extraMessageView)
        configureEditText()

        if (validator != null) {
            textWatcher = createValidationTextWatcher()
        }

        builder.setView(root)
        builder.setNegativeButton(R.string.action_cancel, null)
        builder.setPositiveButton(R.string.action_ok, null)

        if (extraButtonCallback != null) {
            builder.setNeutralButton(extraButtonTextRes, null)
        }

        dialog = builder.create()

        dialog.setOnShowListener {
            val button = this.dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            button.setOnClickListener(onSubmitBtnClickListener)

            if (extraButtonCallback != null) {
                val extraButton = this.dialog.getButton(AlertDialog.BUTTON_NEUTRAL)
                extraButton.setOnClickListener(onExtraBtnClickListener)
            }
        }

        // Show the damn keyboard.
        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        dialog.show()

        finalizeShow(dialog)
    }

    private fun configureExtraMessageView(extraMessageView: TextView) {
        if (extraMessage != null) {
            extraMessageView.visibility = View.VISIBLE
            extraMessageView.text = extraMessage
        }
    }

    private fun configureEditText() {
        if (hint != null) {
            editText.hint = hint
        }

        editText.setSingleLine()
        editText.imeOptions = EditorInfo.IME_ACTION_DONE or imeOptions
        editText.setText(initialValue)
        editText.selectAll()
        editText.inputType = inputType

        if (maxLength >= 0) {
            editText.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))
        }

        editText.setOnEditorActionListener(onEditorActionListener)
    }

    private fun createValidationTextWatcher() = object : SimpleTextWatcher() {
        override fun afterTextChanged(s: Editable) {
            val result = validator?.validate(s.toString())
            if (result != null) {
                updateErrorMessage(result)
            }
        }
    }

    private fun submit() {
        val value = editText.text.toString()

        if (validator != null) {

            val result = validator!!.validate(value)

            if (!result.isValid) {
                updateErrorMessage(result)
                editText.addTextChangedListener(textWatcher)
                return
            }
        }

        onSubmit?.invoke(value)
        dismiss()
    }

    private fun updateErrorMessage(validatorResult: Validator.Result) {
        if (validatorResult.isValid) {
            errorMessageView.visibility = View.INVISIBLE
        } else {
            errorMessageView.visibility = View.VISIBLE
            if (validatorResult.errorMessage != null) {
                errorMessageView.text = validatorResult.errorMessage
            } else {
                errorMessageView.setText(validatorResult.errorMessageId)
            }
        }
    }
}
