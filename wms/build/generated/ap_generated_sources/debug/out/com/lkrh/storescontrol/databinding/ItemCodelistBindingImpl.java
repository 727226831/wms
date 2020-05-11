package com.lkrh.storescontrol.databinding;
import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemCodelistBindingImpl extends ItemCodelistBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tv_tag, 7);
        sViewsWithIds.put(R.id.l_input, 8);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemCodelistBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }
    private ItemCodelistBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.LinearLayout) bindings[8]
            , (android.widget.RelativeLayout) bindings[0]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[6]
            );
        this.rlLayout.setTag(null);
        this.tvTitle1.setTag(null);
        this.tvTitle2.setTag(null);
        this.tvTitle3.setTag(null);
        this.tvTitle4.setTag(null);
        this.tvTitle5.setTag(null);
        this.tvTitle6.setTag(null);
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
        java.lang.String dataField4text = null;
        java.lang.String dataField3text = null;
        java.lang.String dataField6text = null;
        java.lang.String dataField2text = null;
        java.lang.String dataField1text = null;
        java.lang.String dataField5text = null;
        com.lkrh.storescontrol.bean.ConfirmlistBean data = mData;

        if ((dirtyFlags & 0x3L) != 0) {



                if (data != null) {
                    // read data.field4text
                    dataField4text = data.getField4text();
                    // read data.field3text
                    dataField3text = data.getField3text();
                    // read data.field6text
                    dataField6text = data.getField6text();
                    // read data.field2text
                    dataField2text = data.getField2text();
                    // read data.field1text
                    dataField1text = data.getField1text();
                    // read data.field5text
                    dataField5text = data.getField5text();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvTitle1, dataField1text);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvTitle2, dataField4text);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvTitle3, dataField2text);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvTitle4, dataField5text);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvTitle5, dataField3text);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvTitle6, dataField6text);
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