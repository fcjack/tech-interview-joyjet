package com.joyjet.service;

import com.joyjet.domain.InputData;
import com.joyjet.domain.OutputData;

/**
 * Created by jackson on 14/02/17.
 */
public interface CartService {

    OutputData calculate(InputData inputData);
}
