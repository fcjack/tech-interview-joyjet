package com.joyjet.service;

import com.joyjet.domain.InputData;
import com.joyjet.domain.OutputData;

/**
 * A simple interface to the service that has the method used to apply rules.
 *
 * Created by Jackson Coelho on 14/02/17.
 */
public interface CartService {

    OutputData calculate(InputData inputData);
}
