import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import { Stepper, Step, Textarea, Typography } from "@material-tailwind/react";

export default function ReportDetail() {
  const [reportData, setReportData] = useState();
  const [problemStatus, setProblemStatus] = useState();
  const { reportId } = useParams();
  useEffect(() => {
    async function fetchReport() {
      // axios.defaults.headers.common['Authorization'] = token();
      const protectedEndpointResponse = await axios.get(
        `http://localhost:8080/upc/unsecured/v1/get-user-problem/${reportId}`
      );
      setReportData(protectedEndpointResponse.data);
      switch (protectedEndpointResponse.data.userProblemStatus) {
        case "NOT_STARTED":
          setProblemStatus(0);
          break;
        case "IN_PROGRESS":
          setProblemStatus(1);
          break;
        case "END":
          setProblemStatus(2);
          break;
      }
    }
    fetchReport();
  }, [reportId]);
  return (
    <div className="w-full h-full flex flex-col bg-gray-300 items-center">
      <div className="flex flex-col w-full items-center bg-gray-400 p-2">
        <span className="text-xl text-gray-800 font-semibold">
          {reportData?.uuid}
        </span>
      </div>
      <div className="flex flex-row w-3/4 justify-between mt-4">
        <div className="flex flex-row text-xl flex-grow ">
          <div>Data wysłania: </div>
          <div className="font-bold">{reportData?.userProblemStartDate}</div>
        </div>
        <div className="flex flex-row text-xl">
          <div>Data zakończenia: </div>
          <div className="font-bold">{reportData?.userProblemEndDate}</div>
        </div>
      </div>
      <a className="text-lg text-gray-700">Opis problemu</a>
      <div className="w-3/4">
        <Textarea
          value={reportData?.description}
          disabled={true}
          rows={10}
          color="orange"
          label="Opis"
        ></Textarea>
      </div>
      <a className="text-lg text-gray-700">Status zgłoszenia</a>
      <div className="w-3/4 pt-4">
      <Stepper
        activeStep={problemStatus}
      >
        <Step >
          <div className="absolute -bottom-[4.5rem] w-max text-center">
            <Typography
              variant="h6"
              color={problemStatus === 0 ? "blue" : "blue-gray"}
            >
              Nie rozpoczęte
            </Typography>
          </div>
        </Step>
        <Step >
          <div className="absolute -bottom-[4.5rem] w-max text-center">
            <Typography
              variant="h6"
              color={problemStatus === 1 ? "blue" : "blue-gray"}
            >
              W trakcie
            </Typography>
          </div>
        </Step>
        <Step >
          <div className="absolute -bottom-[4.5rem] w-max text-center">
            <Typography
              variant="h6"
              color={problemStatus === 2 ? "blue" : "blue-gray"}
            >
              Zakończone
            </Typography>
          </div>
        </Step>
      </Stepper>
      </div>
      
    </div>
  );
}