package com.lkrh.storescontrol.databinding;
import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemListBindingImpl extends ItemListBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tv_tag, 9);
        sViewsWithIds.put(R.id.l_input, 10);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemListBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }
    private ItemListBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.LinearLayout) bindings[10]
            , (android.widget.RelativeLayout) bindings[0]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[8]
            );
        this.rlLayout.setTag(null);
        this.tvTitle1.setTag(null);
        this.tvTitle2.setTag(null);
        this.tvTitle3.setTag(null);
        this.tvTitle4.setTag(null);
        this.tvTitle5.setTag(null);
        this.tvTitle6.setTag(null);
        this.tvTitle7.setTag(null);
        this.tvTitle8.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.data == variableId) {
            setData((com.lkrh.storescontrol.bean.ConfirmlistBean) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setData(@Nullable com.lkrh.storescontrol.bean.ConfirmlistBean Data) {
        this.mData = Data;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.data);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        java.lang.String dataField2value = null;
        java.lang.String dataField3value = null;
        java.lang.String dataField1value = null;
        java.lang.String dataField6value = null;
        java.lang.String dataField5value = null;
        java.lang.String dataField7value = null;
        java.lang.String dataField4value = null;
        java.lang.String dataField8value = null;
        com.lkrh.storescontrol.bean.ConfirmlistBean data = mData;

        if ((dirtyFlags & 0x3L) != 0) {



                if (data != null) {
                    // read data.field2value
                    dataField2value = data.getField2value();
                    // read data.field3value
                    dataField3value = data.getField3value();
                    // read data.field1value
                    dataField1value = data.getField1value();
                    // read data.field6value
                    dataField6value = data.getField6value();
                    // read data.field5value
                    dataField5value = data.getField5value();
                    // read data.field7value
                    dataField7value = data.getField7value();
                    // read data.field4value
                    dataField4value = data.getField4value();
                    // read data.field8value
                    dataField8value = data.getField8value();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvTitle1, dataField1value);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvTitle2, dataField2value);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvTitle3, dataField3value);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvTitle4, dataField4value);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvTitle5, dataField5value);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvTitle6, dataField6value);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvTitle7, dataField7value);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvTitle8, dataField8value);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): data
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}