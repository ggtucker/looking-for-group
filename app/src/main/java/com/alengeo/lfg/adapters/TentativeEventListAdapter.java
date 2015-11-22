package com.alengeo.lfg.adapters;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alengeo.lfg.R;
import com.alengeo.lfg.models.TentativeEvent;

import java.util.List;

public class TentativeEventListAdapter extends ArrayAdapter<TentativeEvent> {

    private final LayoutInflater inflater;

    public TentativeEventListAdapter(Activity context, List<TentativeEvent> entries) {
        super(context, R.layout.event_list_tentative, entries);
        this.inflater = context.getLayoutInflater();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if(view == null) {
            view = inflater.inflate(R.layout.event_list_tentative, null, true);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        final ViewHolder vh = viewHolder;

        viewHolder.expandable.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                vh.expandable.getViewTreeObserver().removeOnPreDrawListener(this);
                vh.expandable.setVisibility(View.GONE);

                final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                vh.expandable.measure(widthSpec, heightSpec);

                vh.animator = slideAnimator(vh.expandable, 0, vh.expandable.getMeasuredHeight());
                return true;
            }
        });

        viewHolder.header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vh.expandable.getVisibility() == View.GONE) {
                    System.out.println("expand");
                    expand(vh.expandable, vh.animator);
                } else {
                    System.out.println("collapse");
                    collapse(vh.expandable);
                }
            }
        });

        TentativeEvent entry = getItem(position);
        viewHolder.titleView.setText(entry.getTitle());
        viewHolder.descriptionView.setText(entry.getDescription());
        viewHolder.minPeopleView.setText("> " + entry.getMinPeople() + " people");
        viewHolder.maxPeopleView.setText("< " + entry.getMaxPeople() + " people");
        viewHolder.numPeopleView.setText("" + entry.getNumPeople());
        viewHolder.startTimeView.setText("starts: " + entry.getStartTime());
        viewHolder.endTimeView.setText("ends: " + entry.getEndTime());
        viewHolder.ageView.setText("ages: " + entry.getMinAge() + "-" + entry.getMaxAge());

        return view;
    }

    private void expand(LinearLayout expandable, Animator animator) {
        expandable.setVisibility(View.VISIBLE);
        animator.start();
    }

    private void collapse(final LinearLayout expandable) {
        int finalHeight = expandable.getHeight();
        ValueAnimator animator = slideAnimator(expandable, finalHeight, 0);

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                expandable.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        animator.start();
    }

    private ValueAnimator slideAnimator(final LinearLayout expandable, int start, int end) {

        ValueAnimator animator = ValueAnimator.ofInt(start, end);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = expandable.getLayoutParams();
                layoutParams.height = value;
                expandable.setLayoutParams(layoutParams);
            }
        });

        return animator;
    }

    private class ViewHolder {
        public LinearLayout header;
        public LinearLayout expandable;
        public TextView titleView;
        public TextView descriptionView;
        public TextView minPeopleView;
        public TextView maxPeopleView;
        public TextView numPeopleView;
        public TextView startTimeView;
        public TextView endTimeView;
        public TextView ageView;
        public Animator animator;

        public ViewHolder(View view) {
            header = (LinearLayout) view.findViewById(R.id.event_header);
            expandable = (LinearLayout) view.findViewById(R.id.event_expandable);
            titleView = (TextView) view.findViewById(R.id.title);
            descriptionView = (TextView) view.findViewById(R.id.description);
            minPeopleView = (TextView) view.findViewById(R.id.min_people);
            maxPeopleView = (TextView) view.findViewById(R.id.max_people);
            numPeopleView = (TextView) view.findViewById(R.id.num_people);
            startTimeView = (TextView) view.findViewById(R.id.event_start_time);
            endTimeView = (TextView) view.findViewById(R.id.event_end_time);
            ageView = (TextView) view.findViewById(R.id.event_age);
        }
    }
}